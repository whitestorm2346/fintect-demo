const show_pw_btn1 = document.getElementById('show-pw-1')
const hide_pw_btn1 = document.getElementById('hide-pw-1')
const input_new_password = document.getElementById('new-password')

show_pw_btn1.addEventListener('click', () => {
    show_pw_btn1.classList.add('hide')
    hide_pw_btn1.classList.remove('hide')
    input_new_password.setAttribute("type", "text")
})

hide_pw_btn1.addEventListener('click', () => {
    hide_pw_btn1.classList.add('hide')
    show_pw_btn1.classList.remove('hide')
    input_new_password.setAttribute("type", "password")
})

const show_pw_btn2 = document.getElementById('show-pw-2')
const hide_pw_btn2 = document.getElementById('hide-pw-2')
const input_check_password = document.getElementById('check-password')

show_pw_btn2.addEventListener('click', () => {
    show_pw_btn2.classList.add('hide')
    hide_pw_btn2.classList.remove('hide')
    input_check_password.setAttribute("type", "text")
})

hide_pw_btn2.addEventListener('click', () => {
    hide_pw_btn2.classList.add('hide')
    show_pw_btn2.classList.remove('hide')
    input_check_password.setAttribute("type", "password")
})

const reset = () => {
    const input_account = document.getElementById('account')
    input_account.value = ''

    const input_new_password = document.getElementById('new-password')
    input_new_password.value = ''

    const input_check_password = document.getElementById('check-password')
    input_check_password.value = ''
}
