package com.darren.demo.designmodel.decorator;

/**
 * @author : darren
 * @date : 2022/2/11
 */
public class RichPerson extends PersonDecorator {

    public RichPerson(Person person) {
        super(person);
    }

    @Override
    public void desc() {
        super.desc();
        System.out.println("is a rich person");
    }
}
