Shanahan Danualif Erwin - 2206817420
Pemrograman Lanjut B

Tutorial 1

Refleksi 1
You already implemented two new features using Spring Boot. Check again your source code
and evaluate the coding standards that you have learned in this module. Write clean code
principles and secure coding practices that have been applied to your code. If you find any
mistake in your source code, please explain how to improve your code. Please write your
reflection inside the repository's README.md file.

Dalam tutorial 1 Adpro, saya telah berusaha untuk menerapkan prinsip clean code sebaik mungkin.
Nama-nama variable dan fungsi saya buat secara singkat, tetapi masih mudah dipahami 
sehingga dapat meminimalisasi penggunaan comment. Selain itu, fungsi-fungsi yang ada saya
usahakan agar hanya melakukan satu task saja agar jelas makna dari fungsi tersebut. 

Namun, dari segi secure coding practices menurut saya penerapannya masih bisa ditingkatkan 
kembali. Sekarang hanya ada method POST jika user ingin membuat produk baru. Menurut saya bila 
diimplementasikan sistem password agar user hanya dapat mengakses produk miliknya akan membuat 
kode ini lebih aman.

Refleksi 2
1. After writing the unit test, how do you feel? How many unit tests should be made in a
class? How to make sure that our unit tests are enough to verify our program? It would be
good if you learned about code coverage. Code coverage is a metric that can help you
understand how much of your source is tested. If you have 100% code coverage, does
that mean your code has no bugs or errors?

Unit test membuat saya lebih yakin kalau kode aplikasi ini dapat berjalan dengan baik. Unit test 
yang dapat berjalan secara otomatis juga memudahkan saya untuk memastikan program masih berjalan
dengan baik bila ada perubahan. Menurut saya, sebaiknya terdapat unit test bagi setiap fungsi yang
kita buat agar kita yakin fungsi-fungsi tersebut berjalan dengan baik. Mengenai code coverage 
yang 100%, menurut saya masih ada kesempatan kode kita memiiki bugs atau error mengingat banyak hal 
yang tidak bisa diprediksi apa yang seorang user akan input pada program kita.


2. Suppose that after writing the CreateProductFunctionalTest.java along with the
corresponding test case, you were asked to create another functional test suite that
verifies the number of items in the product list. You decided to create a new Java class
similar to the prior functional test suites with the same setup procedures and instance
variables.

What do you think about the cleanliness of the code of the new functional test suite? Will
the new code reduce the code quality? Identify the potential clean code issues, explain
the reasons, and suggest possible improvements to make the code cleaner! Please write
your reflection inside the repository's README.md file.

Menurut saya, hal tersebut dapat mengurangi kebersihan kode karena functional test yang baru
memiliki setup procedures dan instance variable yang sama. Terdapat prinsip don't repeat yourself
yang berarti kita tidak boleh mengulang dua hal yang sama. Sebaiknya kedua functional test tersebut
kita gabung saja agar mengingat kemiripan proses setup dan instance variable agar code menjadi
lebih clean.


Tutorial 2

1. List the code quality issue(s) that you fixed during the exercise and explain your strategy
on fixing them.

Salah satu strategi yang saya terapkan adalah melihat report dari ScoreCard dan SonarCloud saya. Salah satu hal yang saya ubah 
pada kode saya adalah bagian redirect pada package controller. Saya pastikan redirect dari semua method yang saya tulis 
sama persis dengan nama file html yang saya buat agar tidak terjadi bug saat dideploy. Selain itu, berdasarkan laporan dari SonarCloud saya,
saya menambahkan caption pada tabel. Berdasarkan dokumentasi pada SonarCloud, hal ini dilakukan agar dapat memudahkan 
pengguna untuk mengetahui deskripsi dari tabel tersebut


2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current
   implementation has met the definition of Continuous Integration and Continuous
   Deployment? Explain the reasons (minimum 3 sentences)!

Saya rasa implementasi CI/CD pada program saya sudah memenuhi ekspektasi. CI workflow saya akan
berjalan secara otomatis setiap kali ada perubahan pada kode. CI workflow kemudian akan menjalankan
unit testing pada kode saya. Di kode saya juga sudah saya implementasikan code scanning SonarCloud dan ScoreCard untuk
mengecek kualitas dan keamanan dari program yang telah saya tulis secara otomatis. Terakhir, untuk CD workflows saya menggunakan
bantuan koyeb untuk men-deploy website saya. Kode saya akan di-deploy secara otomatis saat ada perubahan pada
main branch saya
