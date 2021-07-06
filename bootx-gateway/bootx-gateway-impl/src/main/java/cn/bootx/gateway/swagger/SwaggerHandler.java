/*
 * Copyright Sywd https://gitee.com/wxdfun/sw
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.bootx.gateway.swagger;

import cn.bootx.gateway.config.SwaggerProvider;
import io.swagger.models.Info;
import io.swagger.models.Swagger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.web.Swagger2ControllerWebFlux;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;


@RestController
public class SwaggerHandler {
    @Autowired(required = false)
    private SecurityConfiguration securityConfiguration;
    @Autowired(required = false)
    private UiConfiguration uiConfiguration;

    private static final Logger LOGGER = LoggerFactory.getLogger(Swagger2ControllerWebFlux.class);
    private static final String HAL_MEDIA_TYPE = "application/hal+json";

    private final SwaggerResourcesProvider swaggerResources;

    @Autowired
    public SwaggerHandler(SwaggerResourcesProvider swaggerResources) {
        this.swaggerResources = swaggerResources;
    }

    @GetMapping("/swagger-resources")
    public Mono<ResponseEntity<?>> swaggerResources() {
        return Mono.just((new ResponseEntity<>(swaggerResources.get(), HttpStatus.OK)));
    }

    @GetMapping("/swagger-resources/configuration/security")
    public Mono<ResponseEntity<SecurityConfiguration>> securityConfiguration() {
        return Mono.just(new ResponseEntity<>(
                Optional.ofNullable(securityConfiguration).orElse(SecurityConfigurationBuilder.builder().build()), HttpStatus.OK));
    }

    @GetMapping("/swagger-resources/configuration/ui")
    public Mono<ResponseEntity<UiConfiguration>> uiConfiguration() {
        return Mono.just(new ResponseEntity<>(
                Optional.ofNullable(uiConfiguration).orElse(UiConfigurationBuilder.builder().build()), HttpStatus.OK));
    }

    /**
     * 防止其他服务不可以用导致网关的swagger打不开
     */
    @GetMapping(SwaggerProvider.API_URI)
    public Mono<ResponseEntity<Swagger>> getDocumentation(){
        Swagger swagger = new Swagger();
        swagger.setBasePath("/gateway");
        swagger.setDefinitions(Collections.emptyMap());
        Info info = new Info();
        info.setTitle("网关");
        swagger.info(info);
        swagger.setPaths(Collections.emptyMap());
        swagger.tags(new ArrayList<>(0));
        return Mono.just(new ResponseEntity<>(swagger, HttpStatus.OK));
    }

}
