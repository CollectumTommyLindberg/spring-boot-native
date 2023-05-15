package se.collectum.palev.rest

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import se.collectum.palev.rest.api.PersonApi
import se.collectum.palev.rest.model.DeletePersonRequest
import se.collectum.palev.rest.model.MovePersonRequest
import se.collectum.palev.rest.model.Person

@RestController
class PersonController : PersonApi {
    val log = LoggerFactory.getLogger(PersonController::class.java)

    override fun addPerson(person: Person?): ResponseEntity<Person> {
        log.info("addPerson - $person")

        val p = Person().apply {
            personalIdNumber = person!!.personalIdNumber
            orgNumber = person!!.orgNumber
            // ... resten
        }

        return ResponseEntity.ok(p)
    }

    override fun deletePerson(identityNumber: String?, request: DeletePersonRequest?): ResponseEntity<Void> {
        log.info("deletePerson - $identityNumber - $request")

        return ResponseEntity.ok().build()
    }

    override fun movePerson(identityNumber: String?, request: MovePersonRequest?): ResponseEntity<Person> {
        log.info("movePerson - $identityNumber - $request")

        val p = Person().apply {
            personalIdNumber = identityNumber
            orgNumber = request!!.orgNumber
            // ... resten
        }

        return ResponseEntity.ok(p)
    }
}
