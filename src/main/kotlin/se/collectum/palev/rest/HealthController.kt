package se.collectum.palev.rest

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import se.collectum.palev.rest.api.HealthApi
import se.collectum.palev.rest.model.CreateSicknessHealthApplicationRequest

@RestController
class HealthController : HealthApi {
    val log = LoggerFactory.getLogger(HealthController::class.java)

    override fun createSicknessHealthApplication(identityNumber: String?, request: CreateSicknessHealthApplicationRequest?): ResponseEntity<Void> {
        log.info("createSicknessHealthApplication - $identityNumber - $request")

        return ResponseEntity.ok().build()
    }
}
