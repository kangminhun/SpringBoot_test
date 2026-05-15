import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api/axios';

function Register() {
  const navigate = useNavigate();

  // 입력값 상태 관리
  const [form, setForm] = useState({
    username: '',
    password: '',
    role: 'CUSTOMER',   // 기본값 고객
  });

  // 입력창 값이 바뀔 때마다 실행
  const handleChange = (e) => {
    setForm({
      ...form,                    // 기존 값 유지
      [e.target.name]: e.target.value,  // 바뀐 값만 업데이트
    });
  };

  // 회원가입 버튼 클릭 시 실행
  const handleSubmit = async (e) => {
    e.preventDefault();   // 페이지 새로고침 방지

    try {
      // 백엔드 POST /api/auth/register 로 요청
      const response = await api.post('/api/auth/register', form);

      alert(response.data);   // "회원가입 성공" 알림
      navigate('/');          // 메인 페이지로 이동

    } catch (error) {
      alert('회원가입 실패');
      console.log(error);
    }
  };

  return (
    <div style={{ textAlign: 'center', marginTop: '100px' }}>
      <h2>회원가입</h2>
      <form onSubmit={handleSubmit}>

        {/* 아이디 입력 */}
        <div>
          <input
            type="text"
            name="username"
            placeholder="아이디"
            value={form.username}
            onChange={handleChange}
          />
        </div>

        {/* 비밀번호 입력 */}
        <div>
          <input
            type="password"
            name="password"
            placeholder="비밀번호"
            value={form.password}
            onChange={handleChange}
          />
        </div>

        {/* 역할 선택 */}
        <div>
          <select name="role" value={form.role} onChange={handleChange}>
            <option value="CUSTOMER">고객</option>
            <option value="SELLER">상인</option>
          </select>
        </div>

        <button type="submit">가입하기</button>
        <button type="button" onClick={() => navigate('/')}>취소</button>

      </form>
    </div>
  );
}

export default Register;