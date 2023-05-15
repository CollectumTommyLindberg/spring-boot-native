package se.collectum.palev.rest

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import se.collectum.palev.rest.api.RemunerationApi
import se.collectum.palev.rest.model.ChangeRemunerationsForEmployeeRequest
import se.collectum.palev.rest.model.Person

@RestController
class RemunerationController : RemunerationApi {
    val log = LoggerFactory.getLogger(RemunerationController::class.java)

    override fun changeRemunerationsForEmployee(identityNumber: String?, request: ChangeRemunerationsForEmployeeRequest?): ResponseEntity<Person> {
        log.info("changeRemunerationsForEmployee - $identityNumber - $request")

        val p = Person().apply {
            personalIdNumber = identityNumber!!
            orgNumber = request!!.orgNumber
            currentMonthSalaries.addAll(request!!.remunerations)
            agreedSalaries.addAll(request!!.remunerations)
        }

        return ResponseEntity.ok(p)
    }
}
