package com.first.weatherforecast.common.model

enum class SkyImage(val image: String ) {

    CLEAR("https://2.bp.blogspot.com/-3ftddMrPj-o/XGqurTsVECI/AAAAAAAAAzA/0WQM-4UhRXAyGihVN6PZ9am0HDs7J9bgwCLcBGAs/s1600/miscanthus-2185806_1920%2B%25E6%258B%25B7%25E8%25B2%259D.jpg"),
    SNOW("https://images8.alphacoders.com/716/thumb-1920-716113.jpg"),
    RAIN("https://phonoteka.org/uploads/posts/2021-06/1623135413_11-phonoteka_org-p-tekstura-mokrogo-stekla-krasivo-11.jpg"),
    DRIZZLE("http://rasfokus.ru/images/photos/medium/b65e8401756a4305a9291e7255a4a70d.jpg"),
    THUNDERSTORM("https://cs2.pikabu.ru/post_img2/big/2014/02/06/1/1391639527_430023906.jpg"),
    CLOUDS("https://www.ridus.ru/images/2021/7/19/1302416/hd_5d0a0e3106.jpeg"),
    MIST("https://tatarstan.ru/file/news/1021_n1859250_big.jpg"),
    SMOKE("https://tatarstan.ru/file/news/1021_n1859250_big.jpg"),
    HAZE("https://pibig.info/uploads/posts/2021-05/thumbs/1620945820_28-pibig_info-p-dim-iz-lesa-priroda-krasivo-foto-33.jpg"),
    DUST("https://pkco.fi/wp-content/uploads/2016/12/pkcotausta-500x383@2x.jpg"),
    FOG("https://tatarstan.ru/file/news/1021_n1859250_big.jpg"),
    SAND("https://www.meteovesti.ru/pics/src/63764390228.jpg"),
    ASH("https://img.theepochtimes.com/assets/uploads/2018/05/13/2018-05-13T010148Z_1_LYNXNPEE4C012_RTROPTP_4_HAWAII-VOLCANO-1200x800.jpg"),
    SQUALL("https://dela.ru/medianew/img/11-4025999.jpg"),
    TORNADO("https://www.thoughtco.com/thmb/sTK2dZOD5cXd_V4zD9WDL1v70R8=/768x0/filters:no_upscale():max_bytes(150000):strip_icc()/GettyImages-577363020-5c6d785d46e0fb0001d046d1.jpg");

    companion object {

        fun buildSkyImage(skyCondition: String): SkyImage {
            return when (skyCondition) {
                "Clear" -> CLEAR
                "Rain" -> RAIN
                "Snow" -> SNOW
                "Drizzle" -> DRIZZLE
                "Thunderstorm" -> THUNDERSTORM
                "Clouds" -> CLOUDS
                "Mist" -> MIST
                "Smoke" -> SMOKE
                "Haze" -> HAZE
                "Dust" -> DUST
                "Fog" -> FOG
                "Sand" -> SAND
                "Ash" -> ASH
                "Squall" -> SQUALL
                "Tornado" -> TORNADO
                else -> throw IllegalStateException("Unsupported sky condition: $SkyImage")
            }
        }

    }

}