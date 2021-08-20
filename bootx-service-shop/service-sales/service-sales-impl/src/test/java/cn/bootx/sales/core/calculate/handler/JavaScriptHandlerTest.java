package cn.bootx.sales.core.calculate.handler;

import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;

import javax.script.*;

class JavaScriptHandlerTest {
    private static final String JAVASCRIPT = "javascript";

    @Test
    public void scriptEngine() throws ScriptException {
        String script =
                "function add(amount,k1, k2, k3){//java 所传参数名，其中o为对象\n" +
                        " \n" +
                        "     var count = Math.floor(amount / k1)\n" +
                        "     \n" +
                        "     var totalAmount = count*k2\n" +
                        "     \n" +
                        "     if (totalAmount>k3){\n" +
                        "         return k3;\n" +
                        "     } else {\n" +
                        "         return totalAmount;\n" +
                        "     }\n" +
                        "}\n" +
                        "//执行函数\n" +
                        "add(amount,k1, k2, k3)";

        ScriptEngine engine = new ScriptEngineManager().getEngineByName(JAVASCRIPT);
        Compilable compilable = (Compilable) engine;
        Bindings bindings = engine.createBindings();
        bindings.put("amount",800);
        bindings.put("k1",50);
        bindings.put("k2",5);
        bindings.put("k3",120);

        CompiledScript jsFunction = compilable.compile(script);
        Object eval = jsFunction.eval(bindings);

        Assert.isTrue(eval.equals(80.0));
    }

}