package com.protobuf;


import com.darren.demo.utils.protobuf.PersonTestProtos;
import com.google.protobuf.ByteString;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author : darren
 * @date : 2022/3/14
 */
public class ProtoBufTest {

    @SneakyThrows
    public static void main(String[] args) {

        //生成personTest对象
        PersonTestProtos.PersonTest.Builder personBuilder = PersonTestProtos.PersonTest.newBuilder();
        //PersonTest赋值
        personBuilder.setName("Darren");
        personBuilder.setEmail("281@qq.com");
        personBuilder.setSex(PersonTestProtos.PersonTest.Sex.Female);

        PersonTestProtos.PersonTest.PhoneNumber.Builder phoneNumberBuilder = PersonTestProtos.PersonTest.PhoneNumber.newBuilder();
        //赋值
        phoneNumberBuilder.setType(PersonTestProtos.PersonTest.PhoneNumber.PhoneType.MOBILE);
        phoneNumberBuilder.setNumber("181458451");

        //personBuilder 设置phoneNumber
        personBuilder.addPhone(phoneNumberBuilder.build());

        //设置personTest对象
        PersonTestProtos.PersonTest personTest = personBuilder.build();

        //序列化和反序列化
        //方式1 byte[]
        byte[] bytes = personTest.toByteArray();
        //反序列化
        PersonTestProtos.PersonTest personTest1 = PersonTestProtos.PersonTest.parseFrom(bytes);
        System.out.println(String.format("1------>反序列化得到的消息,姓名:%s,性别:%s,手机号：%s", personTest1.getName(), personTest1.getSexValue(), personTest1.getPhone(0).getNumber()));

        //方式2 byteString
        //序列化
        ByteString byteString = personTest.toByteString();
        System.out.println(byteString);
        //反序列化
        PersonTestProtos.PersonTest personTestResult = PersonTestProtos.PersonTest.parseFrom(byteString);
        System.out.println(String.format("2------>反序列化得到的消息,姓名:%s,性别:%s,手机号：%s", personTest1.getName(), personTest1.getSexValue(), personTest1.getPhone(0).getNumber()));

        //方式3 InputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        personTest.writeDelimitedTo(byteArrayOutputStream);
        //反序列化,从stream
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        PersonTestProtos.PersonTest personTest2 = PersonTestProtos.PersonTest.parseDelimitedFrom(byteArrayInputStream);
        System.out.println(String.format("3------>反序列化得到的消息,姓名:%s,性别:%s,手机号：%s", personTest1.getName(), personTest1.getSexValue(), personTest1.getPhone(0).getNumber()));
    }
}
