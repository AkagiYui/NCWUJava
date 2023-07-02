package com.dzf.framework.mvc;

import com.dzf.framework.mvc.annotation.Controller;
import com.dzf.framework.mvc.annotation.Service;
import com.dzf.framework.spring.Spring;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author AkagiYui
 */

public class Mvc {

    static {
        List<Class<? extends Annotation>> annotations = Spring.ANNOTATIONS;
        annotations.add(Controller.class);
        annotations.add(Service.class);
    }

    public static void startMvc() {

    }
}
