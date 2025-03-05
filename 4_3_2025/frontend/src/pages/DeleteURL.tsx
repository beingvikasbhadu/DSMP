import React, { useState } from "react";
import { Button, Input, message } from "antd";
import axios from "axios";

const { Search } = Input;

const DeleteURL = () => {
  const [originalUrl, setOriginalUrl] = useState("");
  const [shortUrl, setShortUrl] = useState("");
  const [messageApi, contextHolder] = message.useMessage();

  // DELETE URL Function
  const deleteUrl = async () => {
    if (!shortUrl) {
      messageApi.warning("No URL to delete!");
      return;
    }

    try {
      await axios.delete(`http://localhost:8080/delete/${shortUrl}`);
      messageApi.success("URL deleted successfully!");
      setShortUrl(""); // Clear short URL after deletion
    } catch (error) {
      console.error("Error deleting URL:", error);
      messageApi.error("Failed to delete URL!");
    }
  };

  return (
    <>
      <Search
        placeholder="Enter Original URL"
        allowClear
        enterButton="Generate"
        size="large"
        onSearch={(e) => setOriginalUrl(e)}
      />

      {shortUrl && originalUrl ? (
        <div>
          <Input placeholder={shortUrl} disabled allowClear />

          <div
            style={{
              marginTop: "10px",
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            {contextHolder}
            <Button type="primary" onClick={deleteUrl}>
              Delete
            </Button>
          </div>
        </div>
      ) : null}
    </>
  );
};

export default DeleteURL;
