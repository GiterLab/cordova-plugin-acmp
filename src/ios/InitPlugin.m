//
//  InitPlugin.m
//  PluginTest
//
//  Created by wangxianjin on 2017/9/14.
//
//

#import "InitPlugin.h"
#import <CloudPushSDK/CloudPushSDK.h>

@implementation InitPlugin

-(void)init:(CDVInvokedUrlCommand *)cmd{
    [self initPush];
    [self registerMessageReceive];
    [CloudPushSDK sendNotificationAck:NULL];
}

-(void)initPush{
    NSMutableDictionary *plistDict=[[NSMutableDictionary alloc]initWithContentsOfFile:[[NSBundle mainBundle] pathForResource:@"PushConfig" ofType:@"plist"]];
    NSString *app_key=[plistDict valueForKey:@"AppKey"];
    NSString *app_secret=[plistDict valueForKey:@"AppSecret"];
    [CloudPushSDK asyncInit:app_key appSecret:app_secret callback:^(CloudPushCallbackResult *res) {
        if (res.success) {
            NSLog(@"success");
            [self registerAPNS:[UIApplication sharedApplication]];
        }else{
            NSLog(@"%@",res.error);
        }
    }];

}

- (void)registerAPNS:(UIApplication *)application {
    if ([[[UIDevice currentDevice] systemVersion] floatValue] >= 8.0) {
        // iOS 8 Notifications
        [application registerUserNotificationSettings:
         [UIUserNotificationSettings settingsForTypes:
          (UIUserNotificationTypeSound | UIUserNotificationTypeAlert | UIUserNotificationTypeBadge)
                                           categories:nil]];
        [application registerForRemoteNotifications];
    }
    else {
        // iOS < 8 Notifications
        [[UIApplication sharedApplication] registerForRemoteNotificationTypes:
         (UIRemoteNotificationTypeAlert | UIRemoteNotificationTypeBadge | UIRemoteNotificationTypeSound)];
    }
}



/**
 *    注册推送消息到来监听
 */
- (void)registerMessageReceive {
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(onMessageReceived:)
                                                 name:@"CCPDidReceiveMessageNotification"
                                               object:nil];
}
/**
 *    处理到来推送消息
 *
 *    @param notification
 */
- (void)onMessageReceived:(NSNotification *)notification {
    CCPSysMessage *message = [notification object];
    NSString *title = [[NSString alloc] initWithData:message.title encoding:NSUTF8StringEncoding];
    NSString *body = [[NSString alloc] initWithData:message.body encoding:NSUTF8StringEncoding];
    NSLog(@"Receive message title: %@, content: %@.", title, body);
}



@end
