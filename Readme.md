# Company Resources Application
## Author: Paweł Łącki

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
- Jeśli zmieniasz frontend, przebuduj go przed uruchomieniem backendu.
- Jeśli pojawiają się błędy dotyczące brakujących beanów w testach, używaj `@Mock` i `@InjectMocks` zamiast przestarzałego `@MockBean`.
