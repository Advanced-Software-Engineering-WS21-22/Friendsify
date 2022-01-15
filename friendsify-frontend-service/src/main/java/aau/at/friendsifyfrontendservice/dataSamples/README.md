#### Persons
<table title="Person">
    <tr>
        <th>id_p</th>
        <th>firstName</th>
        <th>lastName</th>
        <th>email</th>
        <th>birthday</th>
        <th>id_geoDB</th>
        <th>city</th>
        <th>password_hash</th>
    </tr>
    <tr>
        <td>0</td>
        <td>Max</td>
        <td>Mustermann</td>
        <td>max@mustermann.de</td>
        <td>01.01.2000</td>
        <td>Q483522</td>
        <td>Villach</td>
        <td>$2a$10$A7tRy7jyQsTKNVcEjkwlauKhSg6deU9bnpNJt2CeQQPkWJPKlhry6 (password)</td>
    </tr>
    <tr>
        <td>1</td>
        <td>Anna</td>
        <td>Mustermann</td>
        <td>anna@mustermann.de</td>
        <td>01.01.2001</td>
        <td>Q483522</td>
        <td>Villach</td>
        <td>$2a$10$v6vYLfGiwxC.Go1HyxKinuzNdhFto9ZW0K8cO6Y1LpNA5mt/YK5mK (house)</td>
    </tr>
    <tr>
        <td>2</td>
        <td>John</td>
        <td>Doe</td>
        <td>john.doe@email.com</td>
        <td>06.06.1990</td>
        <td>Q41753</td>
        <td>Klagenfurt</td>
        <td>$2a$10$suHpLlQe44a4IzfXSx6kUeX1VN6Os1thIBaLnr2OEkkJbeb.RVeVG (animal)</td>
    </tr>
    <tr>
        <td>3</td>
        <td>Hans</td>
        <td>Müller</td>
        <td>hans.m@gmail.com</td>
        <td>18.08.1994</td>
        <td>Q660687</td>
        <td>Velden am Wörthersee</td>
        <td>$2a$10$jyBKNyMulV6YVTgpl0M5EOl9Z2Iy/ncZvVdr.SAztZngb.qjpQJza (car)</td>
    </tr>
    <tr>
        <td>4</td>
        <td>Maria</td>
        <td>Schmidt</td>
        <td>m.schmidt@gmail.com</td>
        <td>01.12.1994</td>
        <td>Q875805</td>
        <td>Pörtschach am Wörthersee</td>
        <td>$2a$10$cQ2liy6Ut0AsaYNoQLBxOujRERYESHTRzBO0CVPuevrrMfpmuVHSi (tree)</td>
    </tr>
    <tr>
        <td>5</td>
        <td>Lukas</td>
        <td>Maier</td>
        <td>lumai@gmx.com</td>
        <td>15.10.1999</td>
        <td>Q494604</td>
        <td>Sankt Veit an der Glan</td>
        <td>$2a$10$SzI/pP8kNZ4z.HcQeRWDp.wgFFpIqc7TKhDI2qTekvBKkmLMoLxIq (computer)</td>
    </tr>
</table>

#### Friends
<table title="Friends">
    <tr>
        <th>id_fs</th>
        <th>id_p_initiator</th>
        <th>id_p_friend</th>
        <th>fs_start_date</th>
        <th>is_timed_out</th>
    </tr>
    <tr>
        <td>0</td>
        <td>3</td>
        <td>4</td>
        <td>31.01.2019</td>
        <td>false</td>
    </tr>
    <tr>
        <td>1</td>
        <td>1</td>
        <td>0</td>
        <td>01.06.2020</td>
        <td>false</td>
    </tr>
    <tr>
        <td>2</td>
        <td>6</td>
        <td>5</td>
        <td>18.02.2021</td>
        <td>false</td>
    </tr>
    <tr>
        <td>3</td>
        <td>4</td>
        <td>5</td>
        <td>21.08.2020</td>
        <td>true</td>
    </tr>
</table> 
