# Star Wars Facts App

Приложение задумывалось для демонстрации навыков работы с внешним API.
Стек технологий: Compose Navigation, Retrofit, OkHttp, Dagger + Hilt, DataStore, Coroutines + Flow

Для персистентного хранения данных был использован DataStore в ознакомительных целях, т.к. опыт работы над Room был получен при работе над [My Pet App](https://github.com/s3r6anita/mypet-vkEdu-project/). 
Одним из важных недостатков оказалось отсутствие автогенерации id для локально сохраненных объектов, которая в Room реализована посредством SQL.

В качестве API было использовано открытое API - https://swapi.dev/api
