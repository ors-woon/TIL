package com.chap01

import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component


@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
open class ProtoTypeBean

@Component
class ProtoTypeClientBean(val bean1: ProtoTypeBean, val bean2: ProtoTypeBean)
