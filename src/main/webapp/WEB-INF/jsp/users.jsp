</head>

<style>
  <%--    Add css for table, rows, column here--%>
</style>
<body>

<div>
  <b>
    ${user}
  </b>
</div>

<div>
  <h1>System Users</h1>
</div>
<div>
  <table>
    <tr>
      <td>User Id</td>
      <td>User Name</td>
      <td>User Surname</td>
      <td>Birth date</td>
      <td>Is Deleted</td>
      <td>Created</td>
      <td>Changed</td>
      <td>Edit</td>
      <td>Delete</td>
    </tr>
    <c:forEach var="user" items="${users}">
      <tr>
        <td>${user.id}</td>
        <td>${user.userName}</td>
        <td>${user.surname}</td>
        <td>${user.birth}</td>
        <td>${user.isDeleted}</td>
        <td><fmt:formatDate value="${user.creationDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td><fmt:formatDate value="${user.modificationDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td>
          <button>Edit</button>
        </td>
        <td>
          <button>Delete</button>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>

</body>
</html>
