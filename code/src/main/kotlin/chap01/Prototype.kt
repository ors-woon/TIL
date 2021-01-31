package chap01

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component


@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class ProtoTypeBean

class ProtoTypeClientBean (val bean1:ProtoTypeBean, val bean2:ProtoTypeBean ){
}