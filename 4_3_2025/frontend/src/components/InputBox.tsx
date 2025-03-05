type inputBoxType = {
  placeholder: string;
  type?: string;
  onChange:(e:React.ChangeEvent<HTMLInputElement>)=>void
};
export const InputBox = ({ placeholder, type, onChange }: inputBoxType) => {
  return (
    <div className="my-2 h-screen w-96">
      <input
        type={type || "text"}
        placeholder={placeholder}
       
        onChange={onChange}
      />
    </div>
  );
};
