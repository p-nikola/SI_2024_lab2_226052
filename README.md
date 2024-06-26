# SI_2024_lab2_226052

### Никола Петрушевски 226052
## Control Flow Graph

![lab2_slika](https://github.com/p-nikola/SI_2024_lab2_226052/assets/56457740/40ac733d-1723-4094-a3fa-aebaa85dbbfd)

## Цикломатската комплексност
За цикломатската комплексност се добива 10. Се пресметува со помош на формулата `V(G) = E - N + 2`, каде што `E` е бројот на ребра и `N` е бројот на јазли.
Во графот на сликата има 37 ребра и 29 јазли па според формулата `37 - 29 + 2 = 10`.

## Тест случаи според Every Branch критериумот

![image](https://github.com/p-nikola/SI_2024_lab2_226052/assets/56457740/5e96b390-9eee-4913-8651-1cee60882372)

### Објаснување

Кај Every Branch методот треба да ги поминеме двата исходи кај јазлите за одлука. 

### 1.`allItems=null, payment = anything`

Во првиот тест пример праќаме празна листа каде што очекуваме програмта да фрли исклучок `RuntimeException("allItems list can't be null!")`и да зврши. Овде payment променливата може да ја добие која било вредност поради тоа што нема никакво влијание врз овој тест пример.

### 2.`allItems = [new Item(null, "1223", 350, 1.5)], payment = 300`

Во вториот тест пример праќаме листа каде што името на производот е null за да го опфатиме исходот `item.getName() == null || item.getName().length() == 0` каде што името на производот ќе биде наместено на unknown. Исто така го тестираме else исходот кај `sum <= payment` којшто треба да врати false и дали ќе влезе во условот `item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0'` каде што е очекувано да не влезе поради тоа што првиот карактер во barcode не е 0. Истиот резултат се добива и ако името на производот е празен стринг односно `allItems = [new Item("", "1223", 350, 1.5)], payment = 300`

### 3.`allItems = [new Item("item1", null, 200, 0)], payment = anything`

Во третиот тест пример праќаме листа каде што barcode на производот е null и очекуваме програмта да фрли искучок `RuntimeException("No barcode!")` и да заврши. Овде исто така payment променливата повторно може да ја добие било која вредност поради тоа што нема никакво влијание врз тест примерот.
  
### 4.`allItems = [new Item("item1", "23code", 200, 0)], payment = anything`

Во четвртиот тест пример праќаме листа каде што barcode има карактер кој не е бројка од 0-9 каде што очекуваме програмата да фрли исклучок `RuntimeException("Invalid character in item barcode!")` и да заврши. И овде payment променливата може да ја добие која било вредност поради тоа што нема никакво влијание врз тест примерот.

### 5.`allItems = [new Item("item1", "0123", 350, 1.5)], payment = 500`

Во петтиот тест пример тестираме дали програмата ќе влезе во условот `item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0'` со тоа што ако влезе ќе треба сумата да ја намали за 30 и на крај прогрмата да го исполни условот `sum <= payment` којшто треба да врати true.


## Тест случаи според Multiple Condition критериумот

![image](https://github.com/p-nikola/SI_2024_lab2_226052/assets/56457740/7ea0b1ac-7c92-4015-af11-42bd56a7460b)

### Објаснување

Кај Multiple Condition критериумот треба да се евалуираат сите јазли во кои се врши одлучување во двете можни насоки (true и false).

Условот `if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0')` има 8 можни комбинации на изрази.
Тие се `T && T && T` `T && T && F` `T && F && T` `F && T && T` `F && F && T` `T && F && F` `F && T && F` `F && F && F`.
Но поради Lazy Evaluation може да се елиминираат некој од комбинациите. Во овој случај од 8 можни комбинации би се намалиле на 4.
Тие се `T && T && T` `T && T && F` `T && F && X` `F && X && X` (каде што X означува дека не е битно дали е Т или F)

### 1.`allItems = [new Item("item1", "01223", 350, 1.5)], payment = 550`

Во првиот тест пример ставаме вредности за да се исполни условот `T && T && T`

### 2. `allItems = [new Item("item1", "1223", 350, 1.5)], payment = 550`

Во вториот тест пример ставаме вредности за да се исполни условот `T && T && F`

### 3. `allItems = [new Item("item1", anything, 350, 0)], payment = 250`

Во третиот тест пример ставаме вредности за да се исполни условот `T && F && X`. Тука вредноста за barcode не е битна поради тоа што проверката `item.getDiscount() > 0` ќе биде false и со Lazy Evaluation нема ни да дојде до проверка на barcode.

### 4. `allItems = [new Item("item1", anything, 250, anything)], payment = 550`

Во четвриот тест пример ставаме вредности за да се исполни условот `F && X && X`. Тука вредноста за discount и barcode не е битна поради тоа што `item.getPrice() > 300` ќе биде false и со Lazy Evaluation нема ни да дојде до проверка на discount и barcode.

