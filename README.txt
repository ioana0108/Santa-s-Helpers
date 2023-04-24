---README---



- am parcurs fisierele de input

- am extras la fiecare pas input-ul din fisierul curent

- am adaugat copiii primiti din input (cu varsta <= 18) in lista mosului

- am facut "Faza 0" separat:
- am parcurs lista mosului si i-am atribuit fiecarui copil urmatoarele:
	- niceScore-ul in istoricul de scor-uri
	- bugetul de cadouri (cu schimbarile elfilor)
- am pus lista prelucrata de copii in lista finala





Important: 
	Am considerat ca o runda are mai multi ani (numberOfYears ani), o runda 
fiind tot ce primesc din fisierul de input, adica date pentru numberOfYears ani.
	- lista in care se retine rezultatul final, cel afisat
in fisierul de output este round_kinds.annualChildren (rezultatul intregii runde)
	- lista annual_kids.children retine lista prelucrata de copii dintr-un singur an
	- la finalul fiecarui an, lista annual_kids.children, se adauga in
round_kinds.annualChildren, care este lista cu rezultatul final





- am facut simularea pe NumberfYears ani

- in fiecare an am incrementat varstele copiilor

- am verificat daca exista update-uri

- in functie de update-urile primite am prelucrat lista de copii

- am setat din nou, ca in faza 0, bugetul alocat fiecarui copil si
cadourile, dupa ce am aplicat schimbarile primite din input si, implicit, 
schimbarile generate de elfi

- am aplica schimbarile elfului Yellow

- la finalul anului, am pus lista de copii prelucrata in
lista annual_kids.children care memoreaza lista finala pentru
un singur an, pe care am pus-o in lista cu rezultatul final
round_kids.annualChildren. Motivul pentru care am folosit 
o lista intermediara (lista_mos) pe care am pus-o la final in lista cu 
copiii din anul respectiv (annual_kids_children) si nu am 
folosit direct annual_kids_children este acela ca am mi-a fost mie mai
clar in implementare sa procedez asa.





Metodele folosite in implementare sunt in clasele:
	- Copil
	- Elf
	- AnnualChanges
	- AnnualKids





Design Patterns:
	- Strategy pentru elfi (Am creat o interfata Elf care aplica strategiile
elfilor Black, Pink si Yellow si cate o clasa pentru fiecare astfel de strategie)

	- Singleton (am facut o clasa SantaSingleton pentru ca am considerat ca un
obiect de tip "Mos Craciun" are sens sa fie instantiat doar o data - mosul este unic -
spre deosebire de copii, cadouri, elfi etc.

of

