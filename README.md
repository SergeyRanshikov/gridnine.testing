Задача по созданию модуля с фильтрами для фильтрации перелетов
Создан модуль по фильтрации перелетов. Фильтрация осушествляется согласно определенным правилам. 
Согласно поставленной задачи созданы классы которые осуществляют отфильтровку перелетов если соблюдаются следуюшие условия: 
Вылет до текущего момента времени. 
Сегменты с датой прилёта раньше даты вылета. 
Перелеты, где общее время, проведённое на земле, превышает два часа (время на земле — это интервал между прилётом одного сегмента и вылетом следующего за ним).
Затем в классе main осуществляется вызов метода, котрый выводит на консоль все отфильтрованные выборки.
Созданы тестовые классы.
Методы описаны в JavaDoc.
