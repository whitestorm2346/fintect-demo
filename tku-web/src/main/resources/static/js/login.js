const show_pw_btn = document.getElementById('show-pw')
const hide_pw_btn = document.getElementById('hide-pw')
const input_password = document.getElementById('password')

show_pw_btn.addEventListener('click', () => {
    show_pw_btn.classList.add('hide')
    hide_pw_btn.classList.remove('hide')

    input_password.setAttribute("type", "text")
})

hide_pw_btn.addEventListener('click', () => {
    hide_pw_btn.classList.add('hide')
    show_pw_btn.classList.remove('hide')

    input_password.setAttribute("type", "password")
})

const reset = () => {
    const input_account = document.getElementById('account')
    input_account.value = ''

    const input_password = document.getElementById('password')
    input_password.value = ''
}