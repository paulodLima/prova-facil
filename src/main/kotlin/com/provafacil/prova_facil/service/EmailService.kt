package com.provafacil.prova_facil.service

import com.provafacil.prova_facil.model.properties.EmailProperties
import com.provafacil.prova_facil.security.JwtUtil
import jakarta.mail.MessagingException
import org.slf4j.LoggerFactory
import org.springframework.core.io.ClassPathResource
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val emailProperties: EmailProperties,
    private val mailSender: JavaMailSender,
    private val jwtUtil: JwtUtil
) {
    private val url = "http://localhost:4200/auth/reset"
    private val log = LoggerFactory.getLogger(EmailService::class.java)

    fun enviarMensagemParaCriarSenha(id: Int?, email: String, nome: String, recuperarSenha: Boolean) {
        if (id != null) {
            val token = jwtUtil.generateToken(id);
            if (!recuperarSenha) {
                val subject = emailProperties.subject.accountCreated
                val bodyTemplate = emailProperties.body.accountCreated

                val body = bodyTemplate
                    .replace("{user}", nome)
                    .replace("{resetPasswordLink}", "$url/$token")
                sendEmail(email, subject, body)
            } else {
                val subject = emailProperties.subject.accountCreated
                val bodyTemplate = emailProperties.body.accountReset

                val body = bodyTemplate
                    .replace("{user}", nome)
                    .replace("{resetPasswordLink}", "$url/$token")
                sendEmail(email, subject, body)
            }

        }
    }

    fun sendEmail(to: String, subject: String, body: String) {
        try {
            val message = mailSender.createMimeMessage()
            val helper = MimeMessageHelper(message, true, "utf-8")

            helper.setFrom("tccreembolso@gmail.com", "Equipe Prova Facil")
            helper.setTo(to)
            helper.setSubject(subject)

            val htmlContent = """
                <html>
                    <body>
                        <p>$body</p>
                        <br>
                        <img src="cid:image001" />
                    </body>
                </html>
            """.trimIndent()

            helper.setText(htmlContent, true)

            val image = ClassPathResource("imagens/prova-facil.png")
            helper.addInline("image001", image)

            mailSender.send(message)
            log.info("✅ Email enviado com sucesso para $to")
        } catch (e: MessagingException) {
            log.error("❌ Falha ao enviar e-mail: ${e.message}", e)
        }
    }
}