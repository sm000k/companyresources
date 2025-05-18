# Company Resources Application
## Author: Paweł Łącki

## Technologie użyte w projekcie

- **Java 17** – główny język backendu
- **Spring Boot 3.4.5** – framework do budowy aplikacji webowych i REST API
- **Spring Data JPA** – obsługa bazy danych i repozytoriów
- **Spring Web / WebFlux / Web Services** – obsługa żądań HTTP, REST, SOAP
- **H2 Database** – wbudowana baza danych do testów i developmentu
- **Lombok** – automatyzacja generowania kodu (gettery, settery, konstruktory)
- **Gradle** – system budowania i zarządzania zależnościami
- **JUnit 5** – testy jednostkowe i integracyjne
- **Mockito** – mockowanie zależności w testach
- **Jakarta XML Binding (JAXB)** – generowanie plików XML
- **Node.js & npm** – budowanie frontendu (jeśli korzystasz z React/Angular)
- **React** – frontend (w katalogu `frontend/my-react-app` lub podobnym)
- **HTML/CSS/JS** – statyczne pliki frontendu w katalogu `src/main/resources/static`

## Wymagania
- Java 17
- Gradle (wrapper `./gradlew` jest dołączony do projektu)
- (opcjonalnie) Node.js & npm jeśli chcesz przebudować frontend

## Jak uruchomić projekt?
1. Sklonuj repozytorium:
   ```bash
   git clone https://github.com/sm000k/companyresources.git
   cd companyresources
   ```
2. W katalogu resources/static znajduje się statyczna skompilowana postać frontendu, która uruchomi się po skompilowaniu backendu.

3. Uruchom backend:
   Z katalogu głównego (`companyresources`):
   ```bash
   ./gradlew bootRun
   ```
   lub na systemie Windows:
   ```cmd
   gradlew bootRun
   ```
   Aplikacja wystartuje pod adresem [http://localhost:8080](http://localhost:8080).

4. Plik .xml znajduje się w folderze: `src/main/resources/invoices`

5. (opcjonalnie) Uruchom testy:
   Aby uruchomić wszystkie testy:
   ```bash
   ./gradlew test
   ```

6. Uzyskaj dostęp do konsoli bazy danych H2:
   Odwiedź [http://localhost:8080/h2-ui](http://localhost:8080/h2-ui)  
   JDBC URL: `jdbc:h2:mem:itresources`  
   Nazwa użytkownika: `sa`  
   Hasło: `password`

---

## Rozwiązywanie problemów

- Upewnij się, że używasz Java 17 lub nowszej (`java -version`).
