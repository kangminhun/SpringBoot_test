import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api/axios';

function Login() {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    username: '',
    password: '',
  });

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await api.post('/api/auth/login', form);

      // 토큰이랑 역할을 브라우저 로컬스토리지에 저장
      localStorage.setItem('token', response.data.token);
      localStorage.setItem('role', response.data.role);
      localStorage.setItem('username', response.data.username);

      alert('로그인 성공!');

      // 역할에 따라 다른 페이지로 이동
      if (response.data.role === 'SELLER') {
        navigate('/seller');      // 상인 페이지로
      } else {
        navigate('/customer');    // 고객 페이지로
      }

    } catch (error) {
      alert('아이디 또는 비밀번호가 틀렸습니다.');
      console.log(error);
    }
  };

  return (
    <div style={{ textAlign: 'center', marginTop: '100px' }}>
      <h2>로그인</h2>
      <form onSubmit={handleSubmit}>

        <div>
          <input
            type="text"
            name="username"
            placeholder="아이디"
            value={form.username}
            onChange={handleChange}
          />
        </div>

        <div>
          <input
            type="password"
            name="password"
            placeholder="비밀번호"
            value={form.password}
            onChange={handleChange}
          />
        </div>

        <button type="submit">로그인</button>
        <button type="button" onClick={() => navigate('/register')}>회원가입</button>

      </form>
    </div>
  );
}

export default Login;