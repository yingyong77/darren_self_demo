package com.darren.demo.designmodel.decorator;

/**
 * 装饰器
 *
 * @author : darren
 * @date : 2022/2/11
 */
public class PersonDecorator implements Person {

    //被修饰对象
    Person person;

    public PersonDecorator(Person person) {
        this.person = person;
    }

    @Override
    public void desc() {
        person.desc();
    }
}
