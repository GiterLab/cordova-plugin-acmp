//
//  InitPlugin.h
//  PluginTest
//
//  Created by wangxianjin on 2017/9/14.
//
//

#import <Cordova/CDV.h>
#import <CloudPushSDK/CloudPushSDK.h>

typedef void(^MessageCallBack)(NSString * jsonmsg);
typedef void(^RomoteNotificationOpen)(NSString *jsonnotify);

@interface InitPlugin : CDVPlugin

@property(nonatomic,copy)MessageCallBack messagecallback;
@property(nonatomic,copy)RomoteNotificationOpen notifycallback;


-(void)init:(CDVInvokedUrlCommand *)cmd;


-(void)bindAccount:(CDVInvokedUrlCommand *)cmd;

-(void)bindTagsandAlias:(CDVInvokedUrlCommand *)cmd;


-(void)unBindAccount:(CDVInvokedUrlCommand *)cmd;

-(void)unBindTagsandAlias:(CDVInvokedUrlCommand *)cmd;


-(void)getDeviceId:(CDVInvokedUrlCommand *)cmd;

-(void)listTags:(CDVInvokedUrlCommand *)cmd;

-(void)listAlias:(CDVInvokedUrlCommand *)cmd;

-(void)removeAlias:(CDVInvokedUrlCommand *)cmd;

-(void)onMessageRes:(CDVInvokedUrlCommand *)cmd;

-(void)onNotificationClick:(CDVInvokedUrlCommand *)cmd;

@end
