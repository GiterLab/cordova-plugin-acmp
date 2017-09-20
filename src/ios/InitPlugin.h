//
//  InitPlugin.h
//  PluginTest
//
//  Created by wangxianjin on 2017/9/14.
//
//

#import <Cordova/CDV.h>
#import <CloudPushSDK/CloudPushSDK.h>

typedef void(^MessageCallBack)(NSString * title,NSString * body);

@interface InitPlugin : CDVPlugin

@property(nonatomic,weak)MessageCallBack messagecallback;

-(void)bindAccountandTagandAlias:(CDVInvokedUrlCommand *)cmd;

-(void)init:(CDVInvokedUrlCommand *)cmd;

@end
