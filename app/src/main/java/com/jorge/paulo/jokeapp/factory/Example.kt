package com.jorge.paulo.jokeapp.factory

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.NoSuchElementException

enum class CouponType(val couponType: String) {
    POINT("POINT"),
    STAMP("STAMP")
}

interface CouponService {
    fun findType(couponType: CouponType): Boolean
}

class PointCouponServiceIpl : CouponService {
    override fun findType(couponType: CouponType): Boolean {
        return couponType == CouponType.POINT
    }

}

class StampCouponServiceIpl : CouponService {
    override fun findType(couponType: CouponType): Boolean {
        return couponType == CouponType.STAMP
    }

}


/*
Você precisará modificar sua classe de fábrica se tiver um novo cupomType

manutenção será difícil e o princípio OCP será violado

OCP: (Princípio Aberto-Fechado)
Entidades de software (classes, módulos, funções, etc.) devem ser abertas para extensão, mas fechadas para alteração.
 */
class ServiceFactoryV1 {
    fun findCouponService(couponType: CouponType): CouponService {
        return when (couponType) {
            CouponType.POINT -> PointCouponServiceIpl()
            CouponType.STAMP -> StampCouponServiceIpl()
            else -> {
                throw NoSuchElementException("Bumm")
            }
        }
    }
}

/*
Modo correto respeitando o OPEN CLOSED PRINCIPLE
crie qntos cupons quiser e essa classe nao sera alterada
 */

class ServiceFactoryV2 {
    private val listCouponService = emptyList<CouponService>()

    @RequiresApi(Build.VERSION_CODES.N)
    fun findCouponService(couponType: CouponType): CouponService {
        return listCouponService.stream()
            .filter { service -> service.findType(couponType) }
            .findFirst()
            .orElseThrow { NoSuchElementException("Ca-Bum") }
    }
}

/*
Usando cache
No caso acima, ele se comporta de forma ineficiente
quando há muitas solicitações iguais.

Ele primeiro varre a memória para encontrar uma implementação e, se não a encontrar na lista completa .

 */
class ServiceFactoryV3 {

    private val listCouponService = emptyList<CouponService>()
    private val couponsFactoryCache = mutableMapOf<CouponType, CouponService>()

    @RequiresApi(Build.VERSION_CODES.N)
    fun findCouponService(couponType: CouponType): CouponService {

        //Encontre o serviço emMap<CouponType, CouponService>
        var couponService = couponsFactoryCache[couponType]
        //Se um serviço for encontrado,return service
        if (couponService != null) return couponService

        /*
        Se não houver serviço correspondente em couponFactoryCache,
        encontre-o na lista completa de cupons e coloque-o no couponFactoryCacheserviço e devolva
         */
        couponService = listCouponService.stream()
            .filter { service -> service.findType(couponType) }
            .findFirst()
            .orElseThrow { NoSuchElementException("Ca-Bum") }

        couponsFactoryCache.put(couponType, couponService)

        return couponService
    }
}

@RequiresApi(Build.VERSION_CODES.N)
fun main() {
    //Se houver várias implementações, você poderá recebê-las como uma Lista.
    val couponService = ServiceFactoryV2().findCouponService(CouponType.POINT)
}
