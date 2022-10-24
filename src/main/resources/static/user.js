async function thisUser() {
    fetch("http://localhost:8080/api/v1/user")
        .then(res => res.json())
        .then(data => {
            // Добавляем информацию в шапку
            $('#headerUsername').append(data.email);
            let roles = data.roles.map(role => " " + role.role.substring(5)).join(' ');
            $('#headerRoles').append("with roles: ").append(roles);

            //Добавляем информацию в таблицу
            let user = `$(
                <tr>
                    <td>${data.id}</td>
                    <td>${data.firstName}</td>
                    <td>${data.lastName}</td>
                    <td>${data.age}</td>
                    <td>${data.email}</td>
                    <td>${roles}</td>
                </tr>)`;
            $('#userInfo').append(user);
        })
}

$(async function() {
    await thisUser();
});
