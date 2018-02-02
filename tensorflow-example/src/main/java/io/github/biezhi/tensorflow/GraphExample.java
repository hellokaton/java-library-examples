package io.github.biezhi.tensorflow;

import org.tensorflow.*;

/**
 * 定义一个简单的图
 */
public class GraphExample {

    public static void main(String[] args) {
        /**
         * 定义一个 graph 类，并在这张图上定义了 foo 与 bar 的两个变量
         */
        try (Graph g = new Graph()) {

            try (Tensor<Integer> t = Tensor.create(30, Integer.class)) {
                g.opBuilder("Const", "foo").setAttr("dtype", t.dataType()).setAttr("value", t).build();
            }

            try (Tensor<Integer> t = Tensor.create(20, Integer.class)) {
                g.opBuilder("Const", "bar").setAttr("dtype", t.dataType()).setAttr("value", t).build();
            }

            try (Session s = new Session(g);
                 Tensor output1 = s.runner().fetch("foo").run().get(0);
                 Tensor output2 = s.runner().fetch("bar").run().get(0)) {

                System.out.println(output1.intValue());
                System.out.println(output2.intValue());
            }
        }
    }
}