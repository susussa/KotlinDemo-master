package com.links.demo.domain

import com.links.demo.data.Forecast
import com.links.demo.data.ForecastResult
import com.links.demo.domain.Forecast as ModelForecast

/**
 * Created by 17604 on 2017/6/10.
 * 这些类必须从数据映射到我们的domain类
 * 将我们所需要的数据拿出来放到domain的类中
 *
 * 数据处理类
 */
class ForecastDataMapper {
    fun convertFromDataModel(forecast: ForecastResult, cityCode: Long): ForecastList = with(forecast) {
        return ForecastList(cityCode, city.name, city.country, convertForecastListToDomain(list))
    }


    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        /**
         * 这一条语句，我们就可以循环这个集合并且返回一个转换后的新的List。
         * Kotlin在List中提供了很多不错的函数操作符，
         * 它们可以在这个List的每个item中应用这个操作并且任何方式转换它们。
         * 对比Java 7，这是Kotlin其中一个强大的功能。我们很快就会查看所有不同的操作符。
         * 知道它们的存在是很重要的，因为它们要方便得多，并可以节省很多时间和模版。
         */
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast = with(forecast) {
        return ModelForecast(-1, dt,
                weather[0].description,
                temp.max.toInt(),
                temp.min.toInt(),
                generateIconUrl(weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"
}