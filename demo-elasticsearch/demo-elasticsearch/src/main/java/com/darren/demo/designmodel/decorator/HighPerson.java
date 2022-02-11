package com.darren.demo.designmodel.decorator;

/**
 * @author : darren
 * @date : 2022/2/11
 */
public class HighPerson extends PersonDecorator {

    public HighPerson(Person person) {
        super(person);
    }

    @Override
    public void desc() {
        super.desc();
        System.out.println("is a handsome boy");
    }
}
