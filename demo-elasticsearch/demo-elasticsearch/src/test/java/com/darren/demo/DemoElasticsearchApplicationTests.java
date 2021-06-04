package com.darren.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoElasticsearchApplicationTests {

    @Test
    public void test() {

        System.out.println(lengthOfLongestSubstring("abcabcdd"));
        System.out.println(lengthOfLongestSubstring("ddddddd"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }


    public int lengthOfLongestSubstring(String str) {

        int maxLength = 0;

        int left = 0;

        //滑动窗口
        Map<Character, Integer> map = new HashMap<>();

        for (int right = 0; right < str.length(); right++) {
            char itemString = str.charAt(right);

            if (map.containsKey(itemString)) {

                left = Math.max(map.get(itemString), left);
            }
            maxLength = Math.max(maxLength, right - left + 1);

            map.put(itemString, right + 1);
        }

        return maxLength;

    }

}

