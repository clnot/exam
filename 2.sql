CREATE OR REPLACE VIEW бисквиты_пред
AS SELECT 
    i.фамилия, i.имя,  i.отчество,
	b.название, s.дата_начала, s.дата_окончания
FROM 
    занятия AS s
JOIN 
    клиенты AS i ON s.код_клиента = i.id
JOIN 
    абонементы AS b ON s.код_абонемента = b.id
