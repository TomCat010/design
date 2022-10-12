package com.my.aliDevelopment.programmingRule.NamingStyle;

/**
 * 编程规约-命名风格
 */
public class NamingStaleDemo {

    /**
     * 1. 【强制】代码中的命名均不能以下划线或美元符号开始，也不能以下划线或美元符号结束。
     * 反例：_name / __name / $name / name_ / name$ / name__
     */

    public static String _1name__name$namename_name$name__;

    /**
     * 2. 【强制】所有编程相关的命名严禁使用拼音与英文混合的方式，更不允许直接使用中文的方式。
     * 说明：正确的英文拼写和语法可以让阅读者易于理解，避免歧义。注意，纯拼音命名方式更要避免采用。
     * 正例：ali / alibaba / taobao / cainiao/ aliyun/ youku / hangzhou 等国际通用的名称，可视同英文。
     * 反例：DaZhePromotion [打折] / getPingfenByName() [评分] / String fw[福娃] / int 某变量 = 3
     */

    /**
     *  3. 【强制】代码和注释中都要避免使用任何语言的种族歧视性词语。
     * 正例：日本人 / 印度人 / blockList / allowList / secondary
     * 反例：RIBENGUIZI / Asan / blackList / whiteList / slave
     */

    /**
     * 4. 【强制】类名使用 UpperCamelCase 风格，但以下情形例外：DO / BO / DTO / VO / AO /
     * PO / UID 等。
     * 正例：ForceCode / UserDO / HtmlDTO / XmlService / TcpUdpDeal / TaPromotion
     * 反例：forcecode / UserDo / HTMLDto / XMLService / TCPUDPDeal / TAPromotion
     */

    /**
     * 5. 【强制】方法名、参数名、成员变量、局部变量都统一使用 lowerCamelCase 风格。
     * 正例： localValue / getHttpMessage() / inputUserId
     */

    /**
     * 6. 【强制】常量命名全部大写，单词间用下划线隔开，力求语义表达完整清楚，不要嫌名字长。
     * 正例：MAX_STOCK_COUNT / CACHE_EXPIRED_TIME
     * 反例：MAX_COUNT / EXPIRED_TIME
     */
    public static String MAX_STOCK_COUNT = "MAX_STOCK_COUNT";

    /**
     * 7. 【强制】抽象类命名使用 Abstract 或 Base 开头；异常类命名使用 Exception 结尾；测试类
     * 命名以它要测试的类的名称开始，以 Test 结尾。
     */

    /**
     * 8. 【强制】类型与中括号紧挨相连来表示数组。
     * 正例：定义整形数组 int[] arrayDemo。
     * 反例：在 main 参数中，使用 String args[]来定义。
     */
    int[] arrayDemo = new int[0];
    int[] arrayDemo1 = {1,2,3,4,5};

    /**
     * 9. 【强制】POJO 类中的任何布尔类型的变量，都不要加 is 前缀，否则部分框架解析会引起序列
     * 化错误。
     * 版本号 制定团队 更新日期 备注
     * 1.7.0 阿里巴巴与全球 Java 社区开发者 2020.08.03 嵩山版，首次发布前后端规约
     * Java 开发手册
     *  2/59
     * 说明：在本文 MySQL 规约中的建表约定第一条，表达是与否的变量采用 is_xxx 的命名方式，所以，需要
     * 在<resultMap>设置从 is_xxx 到 xxx 的映射关系。
     * 反例：定义为基本数据类型 Boolean isDeleted 的属性，它的方法也是 isDeleted()，框架在反向解析的时
     * 候，“误以为”对应的属性名称是 deleted，导致属性获取不到，进而抛出异常。
     */
   //使用diea 生成get与set方法  boolean类型
    class inner1{
        boolean isDeleted;
        Boolean isDeleted1;
        String isDeleted2;

        public boolean isDeleted() {
            return isDeleted;
        }

        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }

        public Boolean getDeleted1() {
            return isDeleted1;
        }

        public void setDeleted1(Boolean deleted1) {
            isDeleted1 = deleted1;
        }

        public String getIsDeleted2() {
            return isDeleted2;
        }

        public void setIsDeleted2(String isDeleted2) {
            this.isDeleted2 = isDeleted2;
        }
    }

    class inner2{
        boolean deleted;
        Boolean deleted1;
        String  deleted2;

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public Boolean getDeleted1() {
            return deleted1;
        }

        public void setDeleted1(Boolean deleted1) {
            this.deleted1 = deleted1;
        }

        public String getDeleted2() {
            return deleted2;
        }

        public void setDeleted2(String deleted2) {
            this.deleted2 = deleted2;
        }
    }

    /**
     * 10.【强制】包名统一使用小写，点分隔符之间有且仅有一个自然语义的英语单词。包名统一使用
     * 单数形式，但是类名如果有复数含义，类名可以使用复数形式。
     * 正例：应用工具类包名为 com.alibaba.ei.kunlun.aap.util、类名为 MessageUtils（此规则参考 spring 的
     * 框架结构）
     */

    /**
     * 11.【强制】避免在子父类的成员变量之间、或者不同代码块的局部变量之间采用完全相同的命名，
     * 使可理解性降低。
     * 说明：子类、父类成员变量名相同，即使是 public 类型的变量也能够通过编译，另外，局部变量在同一方
     * 法内的不同代码块中同名也是合法的，这些情况都要避免。对于非 setter/getter 的参数名称也要避免与成
     * 员变量名称相同。
     */
    class ConfusingName{
        public int stock;
        // 非 setter/getter 的参数名称，不允许与本类成员变量同名
        public void get(String alibaba) {
            if ("condition".equals("condition")) {
                final int money = 666;
                // ...
            }
            for (int i = 0; i < 10; i++) {
                // 在同一方法体中，不允许与其它代码块中的 money 命名相同
                final int money = 15978;
                // ...
            }
        }
    }

    class Son extends ConfusingName {
        // 不允许与父类的成员变量名称相同
        public int stock;
    }

    /**
     * 12.【强制】杜绝完全不规范的缩写，避免望文不知义。
     * 反例：AbstractClass“缩写”成 AbsClass；condition“缩写”成 condi；Function 缩写”成 Fu，此类
     * 随意缩写严重降低了代码的可阅读性。
     */

    /**
     * 13.【推荐】为了达到代码自解释的目标，任何自定义编程元素在命名时，使用尽量完整的单词组
     * 合来表达。
     * Java 开发手册
     *  3/59
     * 正例：对某个对象引用的 volatile 字段进行原子更新的类名为 AtomicReferenceFieldUpdater。
     * 反例：常见的方法内变量为 int a;的定义方式。
     */

    /**
     * 14.【推荐】在常量与变量的命名时，表示类型的名词放在词尾，以提升辨识度。
     * 正例：startTime / workQueue / nameList / TERMINATED_THREAD_COUNT
     * 反例：startedAt / QueueOfWork / listName / COUNT_TERMINATED_THREAD
     */

    /**
     * 15.【推荐】如果模块、接口、类、方法使用了设计模式，在命名时需体现出具体模式。
     * 说明：将设计模式体现在名字中，有利于阅读者快速理解架构设计理念。
     * 正例： public class OrderFactory;
     *  public class LoginProxy;
     *  public class ResourceObserver;
     */

    /**
     * 16.【推荐】接口类中的方法和属性不要加任何修饰符号（public 也不要加），保持代码的简洁
     * 性，并加上有效的 Javadoc 注释。尽量不要在接口里定义变量，如果一定要定义变量，确定
     * 与接口方法相关，并且是整个应用的基础常量。
     * 正例：接口方法签名 void commit();
     *  接口基础常量 String COMPANY = "alibaba";
     * 反例：接口方法定义 public abstract void f();
     * 说明：JDK8 中接口允许有默认实现，那么这个 default 方法，是对所有实现类都有价值的默认实现。
     */
    interface sss{
        void commit();
        String COMPANY = "";
    }

    /**
     * 17.接口和实现类的命名有两套规则：
     * 1）【强制】对于 Service 和 DAO 类，基于 SOA 的理念，暴露出来的服务一定是接口，内部的实现类用
     * Impl 的后缀与接口区别。
     * 正例：CacheServiceImpl 实现 CacheService 接口。
     * 2）【推荐】如果是形容能力的接口名称，取对应的形容词为接口名（通常是–able 的形容词）。
     * 正例：AbstractTranslator 实现 Translatable 接口
     */

    /**
     * 18.【参考】枚举类名带上 Enum 后缀，枚举成员名称需要全大写，单词间用下划线隔开。
     * 说明：枚举其实就是特殊的常量类，且构造方法被默认强制是私有。
     * 正例：枚举名字为 ProcessStatusEnum 的成员名称：SUCCESS / UNKNOWN_REASON。
     */

    /**
     * 19.【参考】各层命名规约：
     * A) Service/DAO 层方法命名规约
     * 1） 获取单个对象的方法用 get 做前缀。
     * 2） 获取多个对象的方法用 list 做前缀，复数结尾，如：listObjects。
     * 3） 获取统计值的方法用 count 做前缀。
     * 4） 插入的方法用 save/insert 做前缀。
     * 5） 删除的方法用 remove/delete 做前缀。
     * 6） 修改的方法用 update 做前缀。
     * B) 领域模型命名规约
     * Java 开发手册
     *  4/59
     * 1） 数据对象：xxxDO，xxx 即为数据表名。
     * 2） 数据传输对象：xxxDTO，xxx 为业务领域相关的名称。
     * 3） 展示对象：xxxVO，xxx 一般为网页名称。
     * 4） POJO 是 DO/DTO/BO/VO 的统称，禁止命名成 xxxPOJO。
     */


}
