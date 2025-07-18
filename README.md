# 🧪 REST API Automation Testing with RestAssured, TestNG, Allure & Video Recording 🎥

Welcome to the API Test Automation Project! This repository showcases how to perform robust API testing using **Java**, **RestAssured**, **TestNG**, and **Allure Report**, enhanced with **video recording** for each test execution.

---

# 🔧 Tech Stack

- ✅ **Java** 17
- 🔁 **Rest Assured** – Framework untuk melakukan HTTP request & verifikasi response
- 📐 **TestNG** – Framework testing berbasis annotation dan suite
- 📊 **Allure Report** – Reporting framework dengan UI interaktif dan support video attachment
- 📦 **Maven** – Untuk dependency & build management

---

## 📁 Project Structure

```plaintext
.
├── src
│   ├── main
│   │   └── java
│   └── test
│       └── java
│           ├── base/
│           │   └── TestBase.java
│           ├── tests/
│           │   └── UserApiTests.java
│           └── utils/
│               ├── Config.java
│               └── JsonValidatorUtil.java
├── allure-results/
├── videos/
├── pom.xml
└── README.md

# 📦 Installation & Setup Guide

Ikuti langkah-langkah berikut untuk menginstal, menjalankan, dan melihat hasil dari API Automation Test Framework ini.

---

## 1. Clone Repository

```bash
git clone https://github.com/your-username/api-testing-framework.git
cd api-testing-framework

## 2. Install Dependencies

Pastikan komputer kamu sudah terinstall:

- Java 17 atau versi lebih baru
- Maven build tool

Lalu jalankan perintah berikut di folder project untuk mengunduh dan mengatur semua dependensi yang dibutuhkan:

```bash
mvn clean install

## 3. Menjalankan Semua Test

Jalankan seluruh test otomatis yang sudah dibuat dengan perintah:

```bash
mvn test

## 4. Menampilkan Laporan

Jalankan perintah ini di terminal pada root project:

```bash
allure serve target/allure-results
