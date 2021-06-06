#**Задача №1 - Скоро deadline**

Необходимо протестировать вход в систему приложения app-deadline с использованием проверочного кода из базы данных

Что нужно сделать
1. Изучить схему БД ([schema.sql](https://github.com/netology-code/aqa-homeworks/blob/master/sql/schema.sql))
2. Создать Docker Container на базе MySQL 8
3. Запустить SUT ([app-deadline.jar](https://github.com/netology-code/aqa-homeworks/blob/master/sql/app-deadline.jar))
4. Решить проблему запуска SUT
5. Решить проблему повтрного запуска SUT
6. Решить проблему шифровки паролей
7. Протестировать вход в систему через веб-интерфейс
8. Проверить реакцию системы на трехкратный неверный ввод пароля