package com.darren.demo.spring.mybatis.handler;

/**
 * Mybatis Enum Handler
 *
 * @author Sky.
 */
//@MappedTypes(value = {MccTypeEnum.class, MccShareTypeEnum.class, UseTypeEnum.class, MccOwnerTypeEnum.class,
//        MccPermissionTypeEnum.class, ActionPermissionEnum.class, TrafficTypeEnum.class, ConcatBirthTypeEnum.class,
//        ConcatIsPrimaryEnum.class, FinanceBillTypeEnum.class, AdGroupStatusEnum.class, AdGroupTypeEnum.class, AdRotationModeEnum.class,
//        AdStatusEnum.class, AdTypeEnum.class, ApprovalStatusEnum.class, AudienceStatusEnum.class, BiddingStrategyTypeEnum.class,
//        CampaignChannelTypeEnum.class, CampaignChannelSubTypeEnum.class, CampaignServingEnum.class, CampaignStatusEnum.class,
//        EffectiveCpmBidSourceEnum.class, ExperimentTypeEnum.class, ExtensionFeedStatusEnum.class, ExperimentTypeEnum.class,
//        KeywordMatchTypeEnum.class, KeywordStatusEnum.class, NegativeKeywordsMatchTypeEnum.class, PaymentModeEnum.class, SearchTermMatchTypeEnum.class,
//        SearchTermStatusEnum.class, ServingStatusEnum.class, PolicyReviewStatusEnum.class, ExtensionFeedTypeEnum.class, UserListRangeForSearchEnum.class,
//        UsersMembershipStatusEnum.class, AudienceUseTypeEnum.class, MccBalanceAccountEnum.class, MccDataSourceEnum.class, AdApprovalStatusEnum.class, FacebookObjectiveEnum.class
//
//})
public class EnumTypeHandler<E extends BaseEnum> extends BaseEnumHandler<E> {

    public EnumTypeHandler(Class<E> type) {
        super(type);
    }

}
