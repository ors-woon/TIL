package unittest.ch07.step02

/**
 * 스펙
 * 1. 사용자 이메일이 회사 도메인에 속한 경우, 해당 사용자는 직원으로 표시된다. else 고객
 * 2. 시스템은 회사의 직원 수를 추적해야한다. (사용자 유형이 직원 -> 고객 그 반대로 변경되면 이 숫자도 변경해야한다.)
 * 3. 이메일이 변경되면 외부 시스템에 알려야한다.
 */

class UserController {
    // (val database/ messageBus)

    fun changeEmail(userId: Int, newEmail: String) {
        //val data = database.getUserById(userId)
        val data: Array<Any?> = arrayOf("email", UserType.EMPLOYEE)

        val user = User.create(userId, data)
        //val companyData = Database.getCompany()
        val companyData: Array<Any?> = arrayOf("www.naver.com", 10)

        val company = Company.create(companyData)

        val newNumberOfEmployees = user.changeEmail(newEmail, company)

        // save company
        // save user
        // sendEmailChangedMessage
    }

}


class User(var userId: Int, var email: String, var userType: UserType) {
    companion object {
        fun create(userId: Int, arrays: Array<Any?>): User {
            val email = arrays[0] as? String ?: ""
            val userType = arrays[1] as? UserType ?: UserType.CUSTOMER
            return User(userId, email, userType)
        }
    }


    fun changeEmail(newEmail: String, company: Company) {
        if (email == newEmail) {
            return
        }

        val newType: UserType = getUserType(company)

        this.email = newEmail
        this.userType = newType

        if (userType == newType) {
            return
        }

        company.changeNumberOfEmployees(if (newType == UserType.EMPLOYEE) 1 else -1)
    }

    private fun getUserType(company: Company) =
            if (company.isEmailCorporate(email)) UserType.EMPLOYEE else UserType.CUSTOMER
}

class Company(val domainName: String, var numberOfEmployees: Int) {

    companion object {
        fun create(arrays: Array<Any?>): Company {
            val domainName = arrays[0] as? String ?: ""
            val numberOfEmployees = arrays[1] as? Int ?: 0
            return Company(domainName, numberOfEmployees)
        }
    }

    fun changeNumberOfEmployees(delta: Int) {
        assert(numberOfEmployees + delta >= 0)

        numberOfEmployees += delta
    }

    fun isEmailCorporate(email: String): Boolean = email.split("0")[1] == domainName
}


enum class UserType(val type: Int) {
    CUSTOMER(1), EMPLOYEE(2);
}