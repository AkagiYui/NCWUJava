package com.akagiyui.testyourcalculation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;

public class Config {
    public static String CONFIG_FILE = "config.yaml";
    private int equationCount; // 算式数量

    /**
     * 从文件加载配置，使用YAML格式
     * @return 是否加载成功
     */
    public boolean loadFromFile() {
        var mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        try {
            var file = new File(CONFIG_FILE);
            var config = mapper.readValue(file, Config.class);

            this.equationCount = config.getEquationCount();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveToFile() {
        var mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        try {
            var file = new File(CONFIG_FILE);
            mapper.writeValue(file, this);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 实例保持器
     */
    private static class SingletonHolder {
        private static final Config INSTANCE = new Config();
    }

    /**
     * 私有化构造方法
     */
    private Config() {}

    /**
     * 获取实例
     * @return 实例
     */
    public static Config getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void setEquationCount(int equationCount) {
        this.equationCount = equationCount;
    }

    public int getEquationCount() {
        return equationCount;
    }
}
