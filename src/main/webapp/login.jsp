<% String error = request.getAttribute("error") != null ? (String) request.getAttribute("error") : ""; %>
    <div class="wrapper">
        <span class="icon-close">
            <ion-icon name="close"></ion-icon>
        </span>
        <div class="form-box login">
            <h2>Login</h2>
            <%if (!error.equals("")) {%>
                <div class="alert alert-danger" role="alert">
                    <%=error%>
                </div>
            <%}%>
            <form action="login" class="signing-force" method="POST">
                <div class="input-box">
                    <span class="icon">
                        <ion-icon name="person"></ion-icon>
                    </span>
                    <input id="username-field" type="text" name="user" required="required"/>
                    <label>Email</label>
                </div>
                <div class="input-box">
                    <span class="icon">
                        <ion-icon name="lock-closed"></ion-icon>
                    </span>
                    <input id="password-field" type="password" name="pass" required="required"/>
                    <label>Password</label>
                </div>
                <div class="remember-forgot">
                    <label>
                        <input type="checkbox">Remember me</label>
                        <a href="#">Forgot Password?</a>
                </div>
                <button type="submit" class="btn">Login</button>
                <div class="login-register">
                    <p>Don't have an account? <a href="#" class="register-link">Register</a> </p>
                </div>
            </form>
        </div>

        <div class="form-box register">
            <h2>Crear cuenta</h2>
            <%if (!error.equals("")) {%>
                <div class="alert alert-danger" role="alert">
                    <%=error%>
                </div>
            <%}%>
            <form action="registro" class="signing-force" method="POST">
                <input type="text" hidden name="action" value="insert"/>
                <div class="input-box">
                    <span class="icon">
                        <ion-icon name="person"></ion-icon>
                    </span>
                    <input id="username-field" type="text" name="username" required>
                    <label>Username</label>
                </div>
                <div class="input-box">
                    <span class="icon">
                        <ion-icon name="mail"></ion-icon>
                    </span>
                    <input id="email-field" type="email" name="email" required>
                    <label>Email</label>
                </div>
                <div class="input-box">
                    <span class="icon">
                        <ion-icon name="lock-closed"></ion-icon>
                    </span>
                    <input id="password-field" type="password" name="pass" required>
                    <label>Password</label>
                </div>
                <div class="remember-forgot">
                    <label>
                        <input type="checkbox"> I agree to the terms & conditions </label>
                </div>
                <button type="submit" class="btn">Register</button>
                <div class="login-register">
                    <p>Already have an account? <a href="#" class="login-link">Login</a> </p>
                </div>
            </form>
        </div>
    </div>