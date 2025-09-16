package com.provafacil.prova_facil.util

import com.provafacil.prova_facil.model.Assunto
import com.provafacil.prova_facil.model.Disciplina
import com.provafacil.prova_facil.repository.DisciplinaRepository
import com.provafacil.prova_facil.repository.ProfessorRepository
import com.provafacil.prova_facil.repository.SerieRepository
import org.springframework.stereotype.Component
import java.io.File
import java.util.UUID

@Component
class ImgUtil(
) {
    fun saveImageAsTempFile(imageData: ByteArray): File {
        val tempFile = File(System.getProperty("java.io.tmpdir"), "${UUID.randomUUID()}.png")

        tempFile.outputStream().use { outputStream ->
            outputStream.write(imageData)
        }
        return tempFile
    }


}