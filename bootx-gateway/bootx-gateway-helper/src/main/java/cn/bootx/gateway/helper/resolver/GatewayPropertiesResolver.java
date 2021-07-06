package cn.bootx.gateway.helper.resolver;

import cn.bootx.gateway.helper.domain.CommonRoute;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.CompositeRouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.net.URI;
import java.util.*;

/**   
* 网关属性解析器
* @author xxm  
* @date 2021/6/7 
*/
@Component
public class GatewayPropertiesResolver implements PropertiesResolver<GatewayProperties> {

    private static final String LB_SCHEME = "lb";
    private static final String LB_PREFIX = "lb://";

    private final RouteDefinitionLocator locator;

    private List<RouteDefinition> cache = new ArrayList<>();

    public GatewayPropertiesResolver(CompositeRouteDefinitionLocator locator) {
        this.locator = locator;
        this.locator.getRouteDefinitions()
                .filter(this::checkRouteDefinition)
                .subscribe(cache::add);
    }

    /**
     * 解析路由
     */
    @Override
    public Map<String, CommonRoute> resolveRoutes(GatewayProperties properties) {
        List<RouteDefinition> routeDefinitions = new ArrayList<>();
        routeDefinitions.addAll(cache);
        routeDefinitions.addAll(properties.getRoutes());
        if (!CollectionUtils.isEmpty(cache)) {
            Map<String, CommonRoute> returnVal = new HashMap<>(32);
            routeDefinitions
                    .forEach(routeDefinition -> {
                        CommonRoute route = new CommonRoute();
                        String id = routeDefinition.getId();
                        URI uri = routeDefinition.getUri();
                        List<PredicateDefinition> predicateDefinitions = routeDefinition.getPredicates();
                        List<FilterDefinition> filterDefinitions = routeDefinition.getFilters();
                        route.setId(id);
                        //从uri中解析serviceId，如果不是lb://{serviceId}则无法获取serviceId
                        //此时采用路由id作为serviceId（由于权限key需要有serviceId）
                        route.setServiceId(resolveServiceId(uri, id));
                        route.setUrl(this.resolveString(predicateDefinitions, "Url"));
                        route.setPath(this.resolveString(predicateDefinitions, "Path"));
                        route.setStripPrefix(this.resolveBoolean(filterDefinitions, "StripPrefix", true));
                        returnVal.put(id, route);
                    });
            return returnVal;
        }
        return Collections.emptyMap();
    }

    private String resolveString(List<PredicateDefinition> predicateDefinitions, String name) {
        if (!CollectionUtils.isEmpty(predicateDefinitions)) {
            return predicateDefinitions.stream()
                    .filter(predicateDefinition -> name.equals(predicateDefinition.getName()) && predicateDefinition.getArgs() != null)
                    .findFirst()
                    .map(predicateDefinition -> predicateDefinition.getArgs().entrySet()
                                .stream()
                            .findFirst()
                            .map(Map.Entry::getValue)
                            .get()
                    ).orElse(null);
        }
        return null;
    }

    /**
     * 检查路由定义
     */
    private boolean checkRouteDefinition(RouteDefinition routeDefinition){
        URI uri = routeDefinition.getUri();
        return uri != null;
    }

    private Set<String> resolveSensitiveHeaders(List<FilterDefinition> filterDefinitions) {
        if (!CollectionUtils.isEmpty(filterDefinitions)) {
            return filterDefinitions.stream()
                    .filter(filterDefinition -> "SensitiveHeaders".equals(filterDefinition.getName()) && filterDefinition.getArgs() != null)
                    .findFirst()
                    .map(filterDefinition -> {
                        Map.Entry<String, String> first = filterDefinition.getArgs().entrySet()
                                .stream().findFirst().get();
                        Set<String> set = new HashSet<>();
                        String[] parts = first.getValue() == null ? new String[]{} : first.getValue().split(",");
                        for (String part : parts){
                            if(!"".equals(part)){
                                set.add(part);
                            }
                        }
                        return set;
                    }).orElseGet(Collections::emptySet);
        }
        return Collections.emptySet();
    }

    private boolean resolveBoolean(List<FilterDefinition> filterDefinitions, String name, boolean defaultValue) {
        if (!CollectionUtils.isEmpty(filterDefinitions)) {
            return filterDefinitions.stream()
                    .filter(filterDefinition -> name.equals(filterDefinition.getName()) && filterDefinition.getArgs() != null)
                    .findFirst()
                    .map(filterDefinition -> {
                        Map.Entry<String, String> first = filterDefinition.getArgs().entrySet()
                                .stream()
                                .findFirst().get();
                        return "1".equals(first.getValue()) || Boolean.parseBoolean(first.getValue());
                    }).orElseGet(() -> false);
        }
        return defaultValue;
    }

    private static String resolveServiceId(URI uri, String defaultValue) {
        if (LB_SCHEME.equals(uri.getScheme())) {
            return uri.getAuthority();
        }
        return defaultValue;
    }

    @EventListener(RefreshRoutesEvent.class)
    public void onApplicationEvent(ApplicationEvent event) {
        cache = new ArrayList<>();
        locator.getRouteDefinitions().subscribe(cache::add);
    }
}
