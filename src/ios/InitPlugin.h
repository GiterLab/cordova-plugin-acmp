<<<<<<< HEAD
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


-(void)init:(CDVInvokedUrlCommand *)cmd;


-(void)bindAccount:(CDVInvokedUrlCommand *)cmd;

-(void)bindTagsandAlias:(CDVInvokedUrlCommand *)cmd;

-(void)unBindAccount:(CDVInvokedUrlCommand *)cmd;

-(void)unBindTagsandAlias:(CDVInvokedUrlCommand *)cmd;


-(void)getDeviceId:(CDVInvokedUrlCommand *)cmd;

-(void)listTags:(CDVInvokedUrlCommand *)cmd;

-(void)listAlias:(CDVInvokedUrlCommand *)cmd;

-(void)removeAlias:(CDVInvokedUrlCommand *)cmd;

@end
=======
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


-(void)init:(CDVInvokedUrlCommand *)cmd;


-(void)bindAccount:(CDVInvokedUrlCommand *)cmd;

-(void)bindTagsandAlias:(CDVInvokedUrlCommand *)cmd;


-(void)unBindAccount:(CDVInvokedUrlCommand *)cmd;

-(void)unBindTagsandAlias:(CDVInvokedUrlCommand *)cmd;


-(void)getDeviceId:(CDVInvokedUrlCommand *)cmd;

-(void)listTags:(CDVInvokedUrlCommand *)cmd;

-(void)listAlias:(CDVInvokedUrlCommand *)cmd;

-(void)removeAlias:(CDVInvokedUrlCommand *)cmd;

@end
>>>>>>> e617e355c1f0ebd6d25754efb22b3fcd477b91da
