
import { useState } from "react";
import { Button, Input, message } from 'antd';
import axios from "axios";


const { Search } = Input;


const ShortenURL = () => {
    const [originalUrl, setOriginalUrl] = useState("")
    const [shortUrl, setShortUrl] = useState("");
    const [messageApi, contextHolder] = message.useMessage();

  const info = () => {
    messageApi.info('URL is Copied!');
    navigator.clipboard.writeText("http://localhost:8080/shorten/"+shortUrl)
  };


  // Function to call backend API
  const generateShortUrl = async (url: string) => {
    try {
      const response = await axios.post("http://localhost:8080/shorten", { url: url }); 
      setShortUrl(response.data.shortCode); // Assuming API response has { shortUrl: "shortened_link" }
    } catch (error) {
      console.error("Error generating short URL:", error);
    }
  };

    return (<>

        <Search
            placeholder="Enter Original URL"
            allowClear
            enterButton="Generate"
            size="large"
            onSearch={(e) => {setOriginalUrl(e)
                generateShortUrl(e); // Call backend API
            }
            }
        />
        {shortUrl && originalUrl ? <div><div >
  
      <Input placeholder={shortUrl} disabled={true} allowClear={true} onChange={() => { }} />
      
  
</div>
<div style={{
           
            marginTop:"10px",
            
            display: 'flex', 
            flexDirection: 'column' ,
            
                   // Enable Flexbox
    justifyContent: 'center', // Center horizontally
    alignItems: 'center', 
     // Center vertically
          }
        }>
        <>
      {contextHolder}
      <Button type="primary" onClick={info}>
         COPY
      </Button>
    </></div></div> : null}
    </>
    );
}

export default ShortenURL;