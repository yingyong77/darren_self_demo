package com.darren.demo.utils.enumutil;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

/**
 * 存放所有的enum类，方便管理
 *
 * @Author： Xiaguangmin
 * @Description:
 * @Date: 2021/5/10 10:45
 */
public class EnumCollections {

    public static List<Integer> DEVICE_TYPE_ID = new LinkedList<>();
    public final static int MSG_CODE_SIZE = MsgCode.values().length;

    static {
        for (DeviceType item : DeviceType.values()) {
            DEVICE_TYPE_ID.add(item.getType());
        }
    }

    /**
     * 设备类型
     */
    public enum DeviceType implements EnumUtils.Compare<Integer> {
        // 消息服务器
        MESSAGE(1),
        //平台管理
        PLATFORM(2),
        // 中继
        RELAY(3),
        // AI
        AI(4),
        // 原设备
        SOURCE(5),
        // 目标设备
        TARGET(6),
        // 手动模式
        MANUAL_MODE(7),
        //一键模式
        ONE_KEY_MODE(8);

        @Getter
        private final int type;

        DeviceType(int type) {
            this.type = type;
        }

        @Override
        public int compare(Integer type) {
            return this.type - type.intValue();
        }
    }

    public enum DataComparerResult implements EnumUtils.Compare<Integer> {
        // 数据未变
        NOT_CHANGE(1),
        // 数据改变
        CHANGE(2),
        // 数据系列改变（内部）
        CHANGE_DETAIL(3),
        // 数据系列改变（外部）
        CHANGE_LIST(4);

        @Getter
        private final int value;

        DataComparerResult(int value) {
            this.value = value;
        }

        @Override
        public int compare(Integer value) {
            return this.value - value.intValue();
        }
    }

    public enum DataClassify {
        // 数据新增
        ADD_LIST,
        // 数据改变, 用于数据返回到前台
        UPDATE_LIST,
        // 数据改变, 用于数据中间状态缓存
        UPDATE_LIST_NEW,
        UPDATE_LIST_OLD,
        // 数据系列改变（内部）
        DELETE_LIST
    }

    /**
     * 消息级别
     */
    public enum MessageLevel implements EnumUtils.Compare<Integer> {
        // 正常
        NORMAL(1),
        // 警告
        WARNING(3),
        // 错误
        ERROR(2);

        @Getter
        private final int level;

        MessageLevel(int level) {
            this.level = level;
        }

        @Override
        public int compare(Integer level) {
            return this.level - level.intValue();
        }
    }

    /**
     * 消息码
     * 目标设备使用的范围 WEB端：1501-1700，消息服务器端：1701-2000
     * AI节点使用的范围 WEB端：2501-2700，消息服务器端：2701-3000
     */
    public enum MsgCode implements EnumUtils.Compare<Integer> {
        // 目标设备：完成代理安装
        TARGET_AGENT_INSTALL_COMPLETED(DeviceType.TARGET, MessageLevel.NORMAL, 1501),
        // 目标设备：安装代理失败,
        TARGET_AGENT_INSTALL_FAILED(DeviceType.TARGET, MessageLevel.ERROR, 1502),
        // 目标设备：已卸载代理,
        TARGET_AGENT_UNINSTALL_COMPLETED(DeviceType.TARGET, MessageLevel.NORMAL, 1503),
        // 目标设备：卸载代理失败,
        TARGET_AGENT_UNINSTALL_FAILED(DeviceType.TARGET, MessageLevel.ERROR, 1504),
        // 目标设备：完成云主机XX创建
        TARGET_CLOUD_CREATE_COMPLETED(DeviceType.TARGET, MessageLevel.NORMAL, 1505),
        // 目标设备：创建云主机XX失败
        TARGET_CLOUD_CREATE_FAILED(DeviceType.TARGET, MessageLevel.ERROR, 1506),
        // 目标设备：已删除云主机XX
        TARGET_CLOUD_DELETE_COMPLETED(DeviceType.TARGET, MessageLevel.NORMAL, 1507),
        // 目标设备：删除云主机XX失败
        TARGET_CLOUD_DELETE_FAILED(DeviceType.TARGET, MessageLevel.ERROR, 1508),
        // 目标设备：完成云主机XX网络配置
        TARGET_CLOUD_CONFIG_NETWORK_COMPLETED(DeviceType.TARGET, MessageLevel.NORMAL, 1509),
        // 目标设备：配置云主机XX网络失败
        TARGET_CLOUD_CONFIG_NETWORK_FAILED(DeviceType.TARGET, MessageLevel.ERROR, 1510),
        // 目标设备：完成云主机XX安全组配置
        TARGET_CLOUD_CONFIG_SECURITY_COMPLETED(DeviceType.TARGET, MessageLevel.NORMAL, 1511),
        // 目标设备：配置云主机XX安全组失败
        TARGET_CLOUD_CONFIG_SECURITY_FAILED(DeviceType.TARGET, MessageLevel.ERROR, 1512),

        // AI节点：已修改配置
        AI_CONFIG_UPDATE_COMPLETED(DeviceType.AI, MessageLevel.NORMAL, 2501),
        // AI节点：修改配置失败,
        AI_CONFIG_UPDATE_FAILED(DeviceType.AI, MessageLevel.ERROR, 2502),
        // AI节点：已导入新特征库
        AI_LIBRARY_IMPORT_COMPLETED(DeviceType.AI, MessageLevel.NORMAL, 2503),
        // AI节点：导入新特征库失败,
        AI_LIBRARY_IMPORT_FAILED(DeviceType.AI, MessageLevel.ERROR, 2504),

        // 平台管理 @author by darren
        USR_ALREADY_LOGGED_IN(DeviceType.PLATFORM, MessageLevel.NORMAL, 3501),
        //平台管理:用户退出-状态:已退出
        USR_ALREADY_LOGGED_OUT(DeviceType.PLATFORM, MessageLevel.NORMAL, 3502),
        //平台管理:用户登录-状态:重复登录
        USR_LOGIN_REPEATED(DeviceType.PLATFORM, MessageLevel.WARNING, 3503),
        //平台管理:创建用户-结果:完成用户创建
        COMPLETED_USER_CREATION_SUCCESS(DeviceType.PLATFORM, MessageLevel.NORMAL, 3504),
        //平台管理:创建用户-结果:创建用户失败
        COMPLETED_USER_CREATION_FAILED(DeviceType.PLATFORM, MessageLevel.ERROR, 3505),
        //平台管理:删除用户-结果:已删除用户
        DELETED_USER_SUCCESS(DeviceType.PLATFORM, MessageLevel.NORMAL, 3506),
        //平台管理:删除用户-结果:删除用户失败
        DELETED_USER_FAILED(DeviceType.PLATFORM, MessageLevel.ERROR, 3507),
        //平台管理:修改用户-结果:完成用户修改
        MODIFIED_USER_INFO_SUCCESS(DeviceType.PLATFORM, MessageLevel.NORMAL, 3508),
        //平台管理:修改用户-结果:修改用户失败
        MODIFIED_USER_INFO_FAILED(DeviceType.PLATFORM, MessageLevel.ERROR, 3509),
        //平台管理:修改密码-结果:修改密码成功
        MODIFIED_USER_PASSWORD_SUCCESS(DeviceType.PLATFORM, MessageLevel.NORMAL, 3530),
        //平台管理:修改密码-结果:修改密码失败
        MODIFIED_USER_PASSWORD_FAILED(DeviceType.PLATFORM, MessageLevel.ERROR, 3531),
        //平台管理:平台授权-结果:平台授权完成
        COMPLETED_PLATFORM_AUTHORIZATION(DeviceType.PLATFORM, MessageLevel.NORMAL, 3510),
        //平台管理:平台授权-结果:平台授权失败
        COMPLETED_PLATFORM_AUTHORIZATION_FAILED(DeviceType.PLATFORM, MessageLevel.ERROR, 3511),
        //平台管理:平台安全设置-状态:开启
        ENABLE_PLATFORM_SECURITY_SETTINGS(DeviceType.PLATFORM, MessageLevel.NORMAL, 3512),
        //平台管理:平台安全设置-状态:关闭
        TURN_OFF_PLATFORM_SECURITY_SETTINGS(DeviceType.PLATFORM, MessageLevel.NORMAL, 3513),
        //平台管理:登录密码-开启登录限制
        ENABLE_LOGIN_PASSWORD_ERROR_TIME_LIMITED(DeviceType.PLATFORM, MessageLevel.NORMAL, 3514),
        //平台管理:登录密码-关闭登录限制
        CLOSED_LOGIN_PASSWORD_ERROR_TIME_LIMITED(DeviceType.PLATFORM, MessageLevel.NORMAL, 3529),
        //平台管理:登录-开启登录白名单
        ENABLE_LOGIN_WHITE_LIST(DeviceType.PLATFORM, MessageLevel.NORMAL, 3515),
        //平台管理:登录-关闭登录白名单
        CLOSED_LOGIN_WHITE_LIST(DeviceType.PLATFORM, MessageLevel.NORMAL, 3516),
        //平台管理:登录-开启密码过期提示
        ENABLE_PASSWORD_EXPIRATION_PROMPT(DeviceType.PLATFORM, MessageLevel.NORMAL, 3517),
        //平台管理:登录-关闭密码过期提示
        TURN_OFF_PASSWORD_EXPIRATION_PROMPT(DeviceType.PLATFORM, MessageLevel.NORMAL, 3518),
        //平台管理:登录-开启密码策略
        ENABLE_PASSWORD_POLICY(DeviceType.PLATFORM, MessageLevel.NORMAL, 3519),
        //平台管理:登录-关闭密码策略
        CLOSED_PASSWORD_POLICY(DeviceType.PLATFORM, MessageLevel.NORMAL, 3520),
        //平台管理:云平台-创建-结果:完成
        CREATED_CLOUD_PLATFORM_SUCCESS(DeviceType.PLATFORM, MessageLevel.NORMAL, 3521),
        //平台管理:云平台-创建-结果:创建失败
        CREATED_CLOUD_PLATFORM_FAILED(DeviceType.PLATFORM, MessageLevel.ERROR, 3522),
        //平台管理:云平台-删除:已删除
        DELETED_CLOUD_PLATFORM_SUCCESS(DeviceType.PLATFORM, MessageLevel.NORMAL, 3523),
        //平台管理:云平台-删除:删除失败
        DELETED_CLOUD_PLATFORM_FAILED(DeviceType.PLATFORM, MessageLevel.ERROR, 3524),
        //平台管理:云平台-上传云镜像-开始
        START_UPLOADING_CLOUD_IMAGES(DeviceType.PLATFORM, MessageLevel.NORMAL, 3525),
        //平台管理:云平台-上传云镜像-完成
        COMPLETED_UPLOADING_CLOUD_IMAGES(DeviceType.PLATFORM, MessageLevel.NORMAL, 3526),
        //平台管理:云平台-配置-完成
        COMPLETED_CLOUD_PLATFORM_CONFIGURATION(DeviceType.PLATFORM, MessageLevel.NORMAL, 3527),
        //平台管理:云平台-配置失败
        FAILED_CLOUD_PLATFORM_CONFIGURATION(DeviceType.PLATFORM, MessageLevel.ERROR, 3528),


        /**
         * 501-1000     一键模式的迁移任务使用的范围
         * 一键模式下：
         * 准备一键任务（来源-任务组名，1次任务仅1条数据），创建云主机xxx（来源-任务组名，多条数据），安装目标代理xxx（来源-任务组名，多条数据），
         * 准备配置任务（来源-源设备ip，多条数据），准备启动迁移（来源-源设备ip，多条数据），完成一键任务（来源-任务组名，仅1条数据）；
         * add by huangchao  2021/3/4
         */
        //准备一键任务
        PREPARE_ONE_KEY_TASK(DeviceType.ONE_KEY_MODE, MessageLevel.NORMAL, 501),
        //创建云主机xxx
        CREATE_VM_ONE_KEY_TASK(DeviceType.ONE_KEY_MODE, MessageLevel.NORMAL, 502),
        //安装目标代理xxx
        INSTALLING_ONE_KEY_TASK(DeviceType.ONE_KEY_MODE, MessageLevel.NORMAL, 503),
        //准备配置任务
        CONFIG_ONE_KEY_TASK(DeviceType.ONE_KEY_MODE, MessageLevel.NORMAL, 504),
        //准备启动迁移
        START_ONE_KEY_TASK(DeviceType.ONE_KEY_MODE, MessageLevel.NORMAL, 505),
        //完成一键任务
        COMPLETED_ONE_KEY_TASK(DeviceType.ONE_KEY_MODE, MessageLevel.NORMAL, 506);

        @Getter
        private final DeviceType type;
        @Getter
        private final MessageLevel level;
        @Getter
        private final int code;

        MsgCode(DeviceType type, MessageLevel level, int code) {
            this.type = type;
            this.level = level;
            this.code = code;
        }

        @Override
        public int compare(Integer code) {
            return this.code - code.intValue();
        }
    }

    /**
     * 各设备类型的消息编码范围，若一个设备类型有多个消息编码则配置多条
     */
    @Getter
    @AllArgsConstructor
    public enum TypeMsgCodeRange {
        MANUAL_MODE_1(DeviceType.MANUAL_MODE, 1, 500),
        ONE_KEY_MODE_1(DeviceType.ONE_KEY_MODE, 501, 1000),
        SOURCE_1(DeviceType.SOURCE, 1001, 1500),
        TARGET_1(DeviceType.TARGET, 1501, 2000),
        RELAY_1(DeviceType.RELAY, 2001, 2500),
        AI_1(DeviceType.AI, 2501, 3000),
        MESSAGE_1(DeviceType.MESSAGE, 3001, 3500),
        PLATFORM_1(DeviceType.PLATFORM, 3501, 4000);

        private final DeviceType type;
        private final int start;
        private final int end;

    }

    @Getter
    @AllArgsConstructor
    public enum TaskState implements EnumUtils.Compare<Integer> {
        NO_CONFIG(0, "noConfigCount"),
        NO_START(1, "noStartCount"),
        MIGRATIONING(2, "migrationingCount"),
        DELIVERY(3, "deliveryCount"),
        STOPED(4, "stopedCount"),
        EXCEPTION(5, "exceptionCount"),
        FINISHED(6, "finishedCount"),
        HISTORY_TASK(7, "historyTaskCount");

        private final int state;
        private final String msg;

        @Override
        public int compare(Integer t) {
            return state - t;
        }
    }
}
