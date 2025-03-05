import  { useState } from 'react';
import {
    DeleteOutlined,
    LinkOutlined,
  MenuFoldOutlined,
  MenuUnfoldOutlined,
  ThunderboltOutlined,
  TransactionOutlined,
  
} from '@ant-design/icons';
import { Button, Layout, Menu, theme } from 'antd';
import ShortenURL from './ShortenURL';
import UpdateURL from './UpdateURL';
import DeleteURL from './DeleteURL';

const { Header, Sider, Content } = Layout;

const Dashboard = () => {
  const [collapsed, setCollapsed] = useState(false);
  const [selectedKey, setSelectedKey] = useState('1');
  const renderContent = () => {
    switch (selectedKey) {
      case '1':
        return <ShortenURL></ShortenURL>;
      case '2':
        return <UpdateURL></UpdateURL>;
      case '3':
        return <DeleteURL></DeleteURL>;
      case '4':
        return <h2>Stats of URL Content</h2>;
      default:
        return <h2>Select a Menu Item</h2>;
    }
  };
  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();

  return (
    <div style={{ 
        display: 'flex', 
        justifyContent: 'center', 
        alignItems: 'center', 
        height: '100vh', // Make it take the full viewport height,

    }}>
    <Layout>
      <Sider trigger={null} collapsible collapsed={collapsed}>
        <div className="demo-logo-vertical" />
        <Menu
          theme="dark"
          mode="inline"
          defaultSelectedKeys={['1']}
          selectedKeys={[selectedKey]} // Keep track of selected key
          onClick={(e) => setSelectedKey(e.key)} // Update content on click
          items={[
            {
              key: '1',
              icon: <LinkOutlined />,
              label: 'Shorten URL',
            },
            {
              key: '2',
              icon: <TransactionOutlined />,
              label: 'Update URL',
            },
            {
              key: '3',
              icon: <DeleteOutlined />,
              label: 'Delete URL',
            }
            ,
            {
                key: '4',
                icon: <ThunderboltOutlined />,
                label: 'Stats of URL',
              }
          ]}
        />
      </Sider>
      <Layout>
        <Header style={{ padding: 0, background: colorBgContainer }}>
          <Button
            type="text"
            icon={collapsed ? <MenuUnfoldOutlined /> : <MenuFoldOutlined />}
            onClick={() => setCollapsed(!collapsed)}
            style={{
              fontSize: '16px',
              width: 64,
              height: 64,
            }}
          />
        </Header>
        <Content
          style={{
            margin: '24px 16px',
            padding: 24,
            minHeight: 480,
            
            background: colorBgContainer,
            borderRadius: borderRadiusLG,
            display: 'flex', 
            flexDirection: 'column' ,
            gap:40,
                   // Enable Flexbox
    justifyContent: 'center', // Center horizontally
    alignItems: 'center', 
     // Center vertically
          }
        }
       
        >
         
         {renderContent()} 
        </Content>
      </Layout>
    </Layout>
    </div>
  );
};

export default Dashboard;