async function auth() {
    let res = await fetch('http://localhost:8080/user');
    return await res.json();
}

upperPanel();
refreshUserPanel();

//Заполнение верхней панели
async function upperPanel() {
    let user = await auth();
    document.getElementById("userUsername").textContent = user.email;
    let roles = "";
    user.roles.forEach(role => {
        roles += role.role.substring(5) + " ";
    })
    document.getElementById("userRoles").textContent = roles;
}

//Обновление панели юзера
async function refreshUserPanel() {
    const tbody = document.querySelector('#userBody');

    let user = await auth();
    let roles = user.roles.map(role => role.role.substring(5));
    let rolesInTable = '';
    roles.forEach(role => {rolesInTable += `<div>${role}</div>`});

    tbody.innerHTML = `<tr>
            <td class="align-middle">${user.id}</td>
            <td class="align-middle">${user.firstName}</td>
            <td class="align-middle">${user.lastName}</td>
            <td class="align-middle">${user.age}</td>    
            <td class="align-middle">${user.email}</td>          
            <td class="align-middle">${rolesInTable}</td>
            </tr>`;
}
