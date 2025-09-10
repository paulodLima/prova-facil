package com.provafacil.prova_facil.controller

import com.provafacil.prova_facil.model.request.PerguntasRequest
import com.provafacil.prova_facil.model.request.ProvaRequest
import com.provafacil.prova_facil.service.ProvaService
import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperExportManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JasperReport
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import net.sf.jasperreports.engine.util.JRLoader
import org.springframework.core.io.ResourceLoader
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RestController
@RequestMapping("api/prova")
class ProvaController(val provaService: ProvaService, val resourceLoader: ResourceLoader) {

    @PostMapping("/gerar")
    fun gerarProva(@RequestBody request: ProvaRequest, principal: Principal): ResponseEntity<List<PerguntasRequest>> {
        val userId = principal.name.toInt();
        val perguntas: List<PerguntasRequest> = provaService.sortearPerguntasPorProfessor(userId, request);
        println("Perguntas sorteadas: $perguntas")
        return ResponseEntity.status(HttpStatus.OK).body(perguntas)
    }


    @PostMapping("/avaliacao/pdf", produces = [MediaType.APPLICATION_PDF_VALUE])
    fun gerarPdf(@RequestBody request: ProvaRequest, principal: Principal): ResponseEntity<ByteArray> {
        val userId = principal.name.toInt()
        val prova = provaService.gerarProva(userId, request)
        val dataHoraAtual = LocalDateTime.now()
        val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm")
        val dataHoraFormatada = dataHoraAtual.format(formato)

        val resource = resourceLoader.getResource("classpath:relatoriosjasper/prova.jasper")
        val inputStream = resource.inputStream
        val parametros = mapOf(
            "logo" to this::class.java.getResourceAsStream("/imagens/seduc.png"),
            "cepmg" to this::class.java.getResourceAsStream("/imagens/CEPMG.png"),
            "serie" to prova.serie,
            "professor" to prova.professor,
            "disciplina" to prova.disciplina,
            "dataHora" to dataHoraFormatada
        )

        // Lista de perguntas
        val dataSource = JRBeanCollectionDataSource(prova.perguntas)

        val jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource)

        val pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint)

        return ResponseEntity.ok()
            .header("Content-Disposition", "inline; filename=prova.pdf")
            .body(pdfBytes)
    }


}