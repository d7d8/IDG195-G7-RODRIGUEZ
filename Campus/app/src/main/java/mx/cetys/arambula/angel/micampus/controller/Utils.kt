package mx.cetys.arambula.angel.micampus.controller

import mx.cetys.arambula.angel.micampus.model.Perfil

class Utils {

    fun buscarPerfil(perfiles: List<Perfil>, matricula: String, password: String): Perfil {
        return perfiles.single { s -> s.matricula == matricula && s.password == password }
    }

    fun buscarPerfilN(perfiles: List<Perfil>, matricula: String, password: String): Perfil? {
        perfiles.listIterator().forEach {
            if (it.matricula == matricula && it.password == password) {
                return it
            }
        }
        return null
    }

}