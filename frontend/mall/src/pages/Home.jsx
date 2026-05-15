import { useNavigate } from 'react-router-dom';

function Home() {
  const navigate = useNavigate(); // 페이지 이동 함수

  return (
    <div style={{ textAlign: 'center', marginTop: '100px' }}>
      <h1>Mall에 오신 것을 환영합니다</h1>
      <button onClick={() => navigate('/login')}>로그인</button>
      <button onClick={() => navigate('/register')}>회원가입</button>
    </div>
  );
}

export default Home;
