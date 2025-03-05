
import { Button, Result } from 'antd';
import { ResultStatusType } from 'antd/es/result';

interface ResultProps {
    status?: ResultStatusType; // status is optional
    title: string;
    redirectUrl?: string; // Optional redirection URL
  }
const Result1=({ status = "success", title }: ResultProps)=>{
  return  <Result
    status={status || "success"}
    title={title}
    
    extra={[
      <Button type="primary" key="console" onClick={() => (window.location.href = "http://localhost:5173/shorten-url")}>
        Go Console
      </Button>,
      ,
    ]}
  />
}

export default Result1;