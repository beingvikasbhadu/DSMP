
import { useEffect, useState } from "react";
import { Button, Input, message, notification } from 'antd';
import axios from "axios";
import Result1 from "../components/Result";


const { Search } = Input;


const UpdateURL = () => {
    const [oldOriginalUrl, setOldOriginalUrl] = useState("")
    const [newOriginalUrl, SetNewOriginalUrl] = useState("")
    const [shortUrl, setShortUrl] = useState("");
    const [messageApi, contextHolder] = message.useMessage();
    const [success,setSuccess]=useState(false)

   


    // Function to call backend API
    const generateShortUrl = async (url: string) => {
        try {
            const response = await axios.post("http://localhost:8080/shorten", { url: url });
            setShortUrl(response.data.shortCode); // Assuming API response has { shortUrl: "shortened_link" }
        } catch (error) {
            console.error("Error generating short URL:", error);
        }
    };

    const info = (message:string) => {
        messageApi.info(message);
        navigator.clipboard.writeText("http://localhost:8080/shorten/"+shortUrl)
      };
     
    const updateShortUrl = async (url: string) => {
        
        try {
            const response = await axios.put("http://localhost:8080/shorten/" + shortUrl, { url: newOriginalUrl });
            setShortUrl(response.data.shortCode); // Assuming API response has { shortUrl: "shortened_link" }
            console.log("data:"+response.data)
            if(response.data!=null)
            {
                setSuccess(true)
                console.log("YESSS");
                
            }else
            {
                info("Something unusual activity happended!")
            }
        } catch (error) {
            console.error("Error generating short URL:", error);
        }
    };

    return (<>
        { newOriginalUrl ? (success ?<header><h3>Updated</h3></header> : <header><h3>Not Updated</h3></header>) :null}
        <Search
            placeholder="Enter Old Original URL"
            allowClear
            enterButton="Generate"
            size="large"
            onSearch={(e) => {
                setOldOriginalUrl(e)
                generateShortUrl(e); // Call backend API
            }
            }
        />
        {shortUrl && oldOriginalUrl ? <div><div  >
            <Search
            style={{margin:"20px"}}
                placeholder="Enter Current Original URL"
                allowClear
                enterButton="Update"
                size="large"
                onSearch={(e) => {
                    SetNewOriginalUrl(e)
                    updateShortUrl(e); // Call backend API
                }
                }
            />
             <div>
                <h4>SHORTEN URL</h4>
             <Input style={{
               
            }}placeholder={shortUrl} disabled={true} allowClear={true} onChange={() => { }} />
             </div>
            


        </div>
            <div style={{

                marginTop: "10px",

                display: 'flex',
                flexDirection: 'column',

                // Enable Flexbox
                justifyContent: 'center', // Center horizontally
                alignItems: 'center',
                // Center vertically
            }
            }>
                <>
                    
                </></div></div> : null}
    </>
    );
}

export default UpdateURL;